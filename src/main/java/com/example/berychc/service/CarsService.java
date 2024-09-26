package com.example.berychc.service;

import com.example.berychc.entity.Cars;
import com.example.berychc.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarsService {

    private final CarsRepository repository;

    /**
     * Метод создания и обновления сущности
     * @param cars
     * @return new car or update car
     */
    public Cars createOrUpdateCar(Cars cars) {
        log.info("Вызван метод - createOrUpdateCar");
        return repository.save(cars);
    }

    /**
     * Метод поиска сущности по id
     * @param id
     * @return id
     */
    public Optional<Cars> getCarById(Integer id) {
        log.info("Вызван  метод - getCarById");
        return repository.findById(id);
    }

    /**
     * Метод удаления сущности
     * @param id
     */
    public void deleteCarById(Integer id) {
        log.info("Вызван метод - deleteCar");
        repository.deleteById(id);
    }
}
