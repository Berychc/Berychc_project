package com.example.berychc.controller;

import com.example.berychc.entity.Cars;
import com.example.berychc.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private CarsService service;

    @PostMapping
    public Cars createCar(@RequestBody Cars cars) {
        return service.createCars(cars);
    }
}
