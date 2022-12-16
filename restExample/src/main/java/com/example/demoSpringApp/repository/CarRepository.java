package com.example.demoSpringApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoSpringApp.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
