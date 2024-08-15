package com.example.uberprojectbookingservice.dtos;

import com.example.uberprojectentityservice.models.Driver;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CreateBookingResponseDto {

    private Long bookingId;

    private String bookingStatus;

    private Optional<Driver> driver;

}
