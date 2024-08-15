package com.example.uberprojectbookingservice.adapters;

import com.example.uberprojectbookingservice.dtos.CreateBookingResponseDto;
import com.example.uberprojectentityservice.models.Booking;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingToCreateBookingResponseDtoAdapter {

    public CreateBookingResponseDto convert(Booking booking) {
        return CreateBookingResponseDto
                .builder()
                .bookingId(booking.getId())
                .bookingStatus(booking.getBookingStatus().toString())
//                .driver(Optional.of(booking.getDriver()))
                .build();
    }
}
