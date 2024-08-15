package com.example.uberprojectbookingservice.adapters;

import com.example.uberprojectbookingservice.dtos.UpdateBookingResponseDto;
import com.example.uberprojectentityservice.models.Booking;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class BookingToUpdateBookingResponseDtoAdapter {

    public UpdateBookingResponseDto convert(Booking booking) {
        return UpdateBookingResponseDto
                .builder()
                .bookingStatus(booking.getBookingStatus())
                .driver(Optional.of(booking.getDriver()))
                .build();
    }
}
