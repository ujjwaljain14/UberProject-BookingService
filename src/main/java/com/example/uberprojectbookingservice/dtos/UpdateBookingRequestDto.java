package com.example.uberprojectbookingservice.dtos;

import com.example.uberprojectentityservice.models.BookingStatus;
import com.example.uberprojectentityservice.models.Driver;

import java.util.Optional;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdateBookingRequestDto {

    private BookingStatus status;

    private Optional<Long> driverId;
}
