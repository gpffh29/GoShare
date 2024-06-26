package com.GoShare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CarOutputDto {
    String car_name;
    String car_type;
    String car_model;
    String car_number;
}
