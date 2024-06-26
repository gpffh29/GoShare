package com.GoShare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SearchDto {
    private String region;
    private String car_type;
    private LocalDate startDate;
    private LocalDate endDate;
}
