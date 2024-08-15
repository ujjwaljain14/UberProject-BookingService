package com.example.uberprojectbookingservice.services;

import com.example.uberprojectentityservice.models.Booking;
import com.example.uberprojectentityservice.models.BookingStatus;
import com.example.uberprojectentityservice.models.Driver;

import java.util.Optional;

public interface BookingService {

    public Booking createBooking(Booking booking);

    public Booking updateBooking(Long bookingId, BookingStatus status, Optional<Long> driverId);
}
