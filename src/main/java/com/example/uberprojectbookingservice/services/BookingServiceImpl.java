package com.example.uberprojectbookingservice.services;

import com.example.uberprojectbookingservice.dtos.DriverLocationDto;
import com.example.uberprojectbookingservice.dtos.NearbyDriversRequestDto;
import com.example.uberprojectbookingservice.respositories.BookingRepository;
import com.example.uberprojectentityservice.models.Booking;
import com.example.uberprojectentityservice.models.BookingStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.lang.model.type.ArrayType;
import java.util.Arrays;
import java.util.List;


@Service
public class BookingServiceImpl implements BookingService {


    private final BookingRepository bookingRepository;

    private final RestTemplate restTemplate;

    private static final String LOCATION_SERVICE = "http://localhost:7777";

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.restTemplate = new RestTemplate();
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

        ResponseEntity<DriverLocationDto[]> result = restTemplate.postForEntity(LOCATION_SERVICE+"/api/v1/location/nearby/drivers", request, DriverLocationDto[].class);

        if(result.getStatusCode().is2xxSuccessful() && result.getBody() != null) {
            List<DriverLocationDto> driverLocations = Arrays.asList(result.getBody());

            driverLocations.forEach(driverLocationDto -> {
                System.out.println(driverLocationDto.getDriverId() + ", lat: " + driverLocationDto.getLatitude() + ", lon: " + driverLocationDto.getLongitude());
            });
        }

        return bookingRepository.save(booking);

    }
}
