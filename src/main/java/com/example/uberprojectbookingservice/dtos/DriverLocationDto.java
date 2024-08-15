package com.example.uberprojectbookingservice.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DriverLocationDto {
    String driverId;
    double latitude;
    double longitude;

    public DriverLocationDto convertDto(String driverId,double latitude,double longitude){
        return DriverLocationDto
                .builder()
                .driverId(driverId)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}

