package com.example.uberprojectbookingservice.dtos;

import com.example.uberprojectentityservice.models.ExactLocation;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateBookingDto {

    private Long passengerId;

    private ExactLocation startLocation;

    private ExactLocation endLocation;

}
