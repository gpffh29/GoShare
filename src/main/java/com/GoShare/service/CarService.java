package com.GoShare.service;

import com.GoShare.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;


}
