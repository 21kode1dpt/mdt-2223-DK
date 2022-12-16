package com.example.demoSpringApp.service;

import com.example.demoSpringApp.model.Car;
import com.example.demoSpringApp.repository.CarRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarService() {

    }

    public void addCar(Car car) {
        if (car.getBrand() != null) {
            if (car.getBrand().toString().toLowerCase().matches(".*bmw.*")) {
                logger.info("BMWs are lame.");
                throw new IllegalStateException("Car can't be added. BMWs suck!");
            } else {
                logger.info("Car added successfully!");
                carRepository.save(car);
            }
        } else {
            logger.info("Please enter the car's brand.");
            throw new IllegalStateException("Car brand not entered!");
        }
    }

    public List<Car> getAllCars() {
        if (this.carRepository == null) {
            logger.info("No cars found in the repository!");
            throw new IllegalStateException("No cars found in the repository!");
        } else {
            return carRepository.findAll();
        }

    }

    public void updateCarInfo(Car car) {
        carRepository.findById(car.getVin()).ifPresentOrElse((value) -> {

            logger.info("Car updated successfully!");
            carRepository.save(car);
        }, () -> {
            // System.out.println("Car not found!");
            logger.info("Car not in repository.");
            throw new IllegalStateException("Car not found!");
        });

    }
}
