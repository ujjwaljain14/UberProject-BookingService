package com.example.uberprojectbookingservice.adapters;

import com.example.uberprojectbookingservice.dtos.CreateBookingDto;
import com.example.uberprojectbookingservice.dtos.CreateBookingResponseDto;
import com.example.uberprojectbookingservice.respositories.PassengerRepository;
import com.example.uberprojectentityservice.models.Booking;
import com.example.uberprojectentityservice.models.ExactLocation;
import com.example.uberprojectentityservice.models.Passenger;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateBookingDtoToBookingAdapter {
    PassengerRepository passengerRepository;
    public CreateBookingDtoToBookingAdapter(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
    public Booking convert(CreateBookingDto createBookingDto) {
        Optional<Passenger> passenger = passengerRepository.findById(createBookingDto.getPassengerId());

        return Booking
                .builder()
                .startLocation(createBookingDto.getStartLocation())
                .endLocation(createBookingDto.getEndLocation())
                .passenger(passenger.get())
                .build();
    }
}
