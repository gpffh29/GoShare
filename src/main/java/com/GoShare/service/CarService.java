package com.GoShare.service;

import com.GoShare.codefAPI.CarOutputDto;
import com.GoShare.entity.Car;
import com.GoShare.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;

    public void saveCar(CarOutputDto carOutputDto){
        Car car = new Car();
        car.setCar_type(carOutputDto.getCar_type());
        car.setCar_name(car.getCar_name());
        car.setCar_model(car.getCar_model());
        carRepository.save(car);
    }
}
