package com.GoShare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CarDto {
    private String car_id;
    private String car_name;
    private String car_model;
    private String car_type;
}
