package com.example.berychc.service;

import com.example.berychc.entity.Cars;
import com.example.berychc.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarsService {

    private final CarsRepository repository;

    public Cars createCars(Cars cars){
        return repository.save(cars);
    }
}
