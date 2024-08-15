package com.example.uberprojectbookingservice.services;

import com.example.uberprojectbookingservice.apis.LocationServiceApi;
import com.example.uberprojectbookingservice.dtos.DriverLocationDto;
import com.example.uberprojectbookingservice.dtos.NearbyDriversRequestDto;
import com.example.uberprojectbookingservice.respositories.BookingRepository;
import com.example.uberprojectbookingservice.respositories.DriverRepository;
import com.example.uberprojectentityservice.models.Booking;
import com.example.uberprojectentityservice.models.BookingStatus;
import com.example.uberprojectentityservice.models.Driver;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class BookingServiceImpl implements BookingService {


    private final BookingRepository bookingRepository;

    private final LocationServiceApi locationServiceApi;
    private final DriverRepository driverRepository;
//    private final RestTemplate restTemplate;

//    private static final String LOCATION_SERVICE = "http://localhost:7777";

    public BookingServiceImpl(BookingRepository bookingRepository, LocationServiceApi locationServiceApi, DriverRepository driverRepository) {
        this.bookingRepository = bookingRepository;
//        this.restTemplate = new RestTemplate();
        this.locationServiceApi = locationServiceApi;
        this.driverRepository = driverRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {

        booking.setBookingStatus(BookingStatus.ASSIGNING_DRIVER);
        Booking newBooking = bookingRepository.save(booking);

        NearbyDriversRequestDto request = NearbyDriversRequestDto
                .builder()
                .latitude(newBooking.getStartLocation().getLatitude())
                .longitude(newBooking.getStartLocation().getLongitude())
                .build();

        processNearByDriversAsync(request);
//
//        ResponseEntity<DriverLocationDto[]> result = restTemplate.postForEntity(LOCATION_SERVICE+"/api/v1/location/nearby/drivers", request, DriverLocationDto[].class);
//
//        if(result.getStatusCode().is2xxSuccessful() && result.getBody() != null) {
//            List<DriverLocationDto> driverLocations = Arrays.asList(result.getBody());
//
//            driverLocations.forEach(driverLocationDto -> {
//                System.out.println(driverLocationDto.getDriverId() + ", lat: " + driverLocationDto.getLatitude() + ", lon: " + driverLocationDto.getLongitude());
//            });
//        }

        return bookingRepository.save(booking);

    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status, Optional<Long> driverId) {
        Optional<Driver> driver = driverRepository.findById(driverId.get());
        if(driver.isPresent()) {
            bookingRepository.updateBookingStatusById(bookingId, status, Optional.of(driver.get()));
            Optional<Booking> booking = bookingRepository.findById(bookingId);
            return booking.get();
        }
        return null;
    }

    private void processNearByDriversAsync(NearbyDriversRequestDto requestDto) {
        Call<DriverLocationDto[]> call = locationServiceApi.getNearbyDrivers(requestDto);
        call.enqueue(new Callback<DriverLocationDto[]>() {
            @Override
            public void onResponse(Call<DriverLocationDto[]> call, Response<DriverLocationDto[]> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<DriverLocationDto> driverLocations = Arrays.asList(response.body());

                    driverLocations.forEach(driverLocationDto -> {
                        System.out.println(driverLocationDto.getDriverId() + ", lat: " + driverLocationDto.getLatitude() + ", lon: " + driverLocationDto.getLongitude());
                    });
                }else{
                    System.out.println("Request failed");
                }
            }

            @Override
            public void onFailure(Call<DriverLocationDto[]> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
