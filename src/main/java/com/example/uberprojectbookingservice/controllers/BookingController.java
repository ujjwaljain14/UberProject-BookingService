package com.example.uberprojectbookingservice.controllers;

import com.example.uberprojectbookingservice.adapters.BookingToCreateBookingResponseDtoAdapter;
import com.example.uberprojectbookingservice.adapters.CreateBookingDtoToBookingAdapter;
import com.example.uberprojectbookingservice.dtos.CreateBookingDto;
import com.example.uberprojectbookingservice.dtos.CreateBookingResponseDto;
import com.example.uberprojectbookingservice.services.BookingServiceImpl;
import com.example.uberprojectentityservice.models.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {

    private final CreateBookingDtoToBookingAdapter createBookingDtoToBookingAdapter;
    private final BookingToCreateBookingResponseDtoAdapter bookingToCreateBookingResponseDtoAdapter;
    private final BookingServiceImpl bookingServiceImpl;

    public BookingController(CreateBookingDtoToBookingAdapter createBookingDtoToBookingAdapter, BookingToCreateBookingResponseDtoAdapter bookingToCreateBookingResponseDtoAdapter, BookingServiceImpl bookingServiceImpl) {
        this.createBookingDtoToBookingAdapter = createBookingDtoToBookingAdapter;
        this.bookingToCreateBookingResponseDtoAdapter = bookingToCreateBookingResponseDtoAdapter;
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CreateBookingResponseDto> createBooking(@RequestBody CreateBookingDto createBookingDto) {

        Booking booking = createBookingDtoToBookingAdapter.convert(createBookingDto);
        Booking newBooking = bookingServiceImpl.createBooking(booking);
        return new ResponseEntity<>(bookingToCreateBookingResponseDtoAdapter.convert(newBooking), HttpStatus.CREATED);
    }
}
