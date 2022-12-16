package com.example.demoSpringApp.controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;

import com.example.demoSpringApp.model.Car;
import com.example.demoSpringApp.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @PutMapping
    public void updateCarInfo(@RequestBody Car car) {

        carService.updateCarInfo(car);
    }

}
