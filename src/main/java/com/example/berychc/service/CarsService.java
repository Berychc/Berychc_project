package com.example.berychc.service;

import com.example.berychc.entity.Cars;
import com.example.berychc.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
    public Cars getCarById(Integer id) {
        log.info("Вызван  метод - GetCarById");
        List<Cars> cars = repository.findAll();
        return cars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Car with id " + id + " not found"));
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
