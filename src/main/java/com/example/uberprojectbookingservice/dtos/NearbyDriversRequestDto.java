package com.example.uberprojectbookingservice.dtos;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class NearbyDriversRequestDto {
    double latitude;
    double longitude;
}
