package com.example.uberprojectbookingservice.controllers;

import com.example.uberprojectbookingservice.adapters.BookingToCreateBookingResponseDtoAdapter;
import com.example.uberprojectbookingservice.adapters.BookingToUpdateBookingResponseDtoAdapter;
import com.example.uberprojectbookingservice.adapters.CreateBookingDtoToBookingAdapter;
import com.example.uberprojectbookingservice.dtos.CreateBookingDto;
import com.example.uberprojectbookingservice.dtos.CreateBookingResponseDto;
import com.example.uberprojectbookingservice.dtos.UpdateBookingRequestDto;
import com.example.uberprojectbookingservice.dtos.UpdateBookingResponseDto;
import com.example.uberprojectbookingservice.services.BookingServiceImpl;
import com.example.uberprojectentityservice.models.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {

    private final CreateBookingDtoToBookingAdapter createBookingDtoToBookingAdapter;
    private final BookingToCreateBookingResponseDtoAdapter bookingToCreateBookingResponseDtoAdapter;
    private final BookingServiceImpl bookingServiceImpl;
    private final BookingToUpdateBookingResponseDtoAdapter bookingToUpdateBookingResponseDtoAdapter;

    public BookingController(CreateBookingDtoToBookingAdapter createBookingDtoToBookingAdapter, BookingToCreateBookingResponseDtoAdapter bookingToCreateBookingResponseDtoAdapter, BookingServiceImpl bookingServiceImpl, BookingToUpdateBookingResponseDtoAdapter bookingToUpdateBookingResponseDtoAdapter) {
        this.createBookingDtoToBookingAdapter = createBookingDtoToBookingAdapter;
        this.bookingToCreateBookingResponseDtoAdapter = bookingToCreateBookingResponseDtoAdapter;
        this.bookingServiceImpl = bookingServiceImpl;
        this.bookingToUpdateBookingResponseDtoAdapter = bookingToUpdateBookingResponseDtoAdapter;
    }

    @PostMapping
    public ResponseEntity<CreateBookingResponseDto> createBooking(@RequestBody CreateBookingDto createBookingDto) {

        Booking booking = createBookingDtoToBookingAdapter.convert(createBookingDto);
        Booking newBooking = bookingServiceImpl.createBooking(booking);
        return new ResponseEntity<>(bookingToCreateBookingResponseDtoAdapter.convert(newBooking), HttpStatus.CREATED);
    }

    @PatchMapping("/{bookingId}")
    public  ResponseEntity<UpdateBookingResponseDto> updateBooking(@RequestBody UpdateBookingRequestDto updateBookingRequestDto, @PathVariable Long bookingId) {
        Booking booking = bookingServiceImpl.updateBooking(bookingId,updateBookingRequestDto.getStatus(),updateBookingRequestDto.getDriverId());
        UpdateBookingResponseDto response = bookingToUpdateBookingResponseDtoAdapter.convert(booking);
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}
