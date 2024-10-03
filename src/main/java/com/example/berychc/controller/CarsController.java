package com.example.berychc.controller;

import com.example.berychc.entity.Cars;
import com.example.berychc.service.CarsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestController
@RequestMapping("/cars")
@Tag(name = "Контроллер для работы с данными о машине")
public class CarsController {

    @Autowired
    private CarsService service;

    @PostMapping
    public ResponseEntity<?> createOrUpdateCar(@RequestBody Cars cars) {
        try {
            Cars savedCar = service.createOrUpdateCar(cars);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);  // Возвращаем статус 201 CREATED и тело ответа с сохранённой машиной
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(cars + "Не найдена");  // Если переданный идентификатор (если есть) не найден
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка");  // Общая ошибка
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Integer id) {
        try {
            Cars car = service.getCarById(id);
            return ResponseEntity.ok(car);  // Возвращаем статус 200 OK и объект автомобиля
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());  // Если автомобиль не найден
        } catch (Exception e) {
            log.error("Ошибка при получении автомобиля", e);  // Логируем общую ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");  // Общая ошибка
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            service.deleteCarById(id);
            return ResponseEntity.ok().build();  // Возвращаем статус 200 OK без тела ответа
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Машина с ID " + id + " не найдена"); // Возвращаем статус 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при удалении машины"); // Обработка других ошибок
        }
    }
}
