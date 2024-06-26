package com.GoShare.service;

import com.GoShare.dto.CarOutputDto;
import com.GoShare.entity.Car;
import com.GoShare.entity.Member;
import com.GoShare.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;

    public void saveCar(CarOutputDto carOutputDto, Member member){
        Car car = new Car();
        car.setCar_type(carOutputDto.getCar_type());
        car.setCar_name(carOutputDto.getCar_name());
        car.setCar_model(carOutputDto.getCar_model());
        car.setCar_number(carOutputDto.getCar_number());
        car.setMember(member);
        carRepository.save(car);
    }

    public Optional<Car> findCar(Long id){
        return carRepository.findById(id);
    }
}
