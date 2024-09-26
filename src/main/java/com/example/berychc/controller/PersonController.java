package com.example.berychc.controller;

import com.example.berychc.entity.Person;
import com.example.berychc.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestController
@RequestMapping("/person")
@Tag(name = "Контроллер для работы с данными о персоне")
public class PersonController {
    
    @Autowired
    private PersonService service;

    @PostMapping
    public ResponseEntity<?> createOrUpdatePerson(@RequestBody Person person) {
        try {
            Person savedPerson = service.createOrUpdatePerson(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);  // Возвращаем статус 201 CREATED и тело ответа с сохранённой машиной
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(person + "Не найдена");  // Если переданный идентификатор (если есть) не найден
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка");  // Общая ошибка
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Integer id) {
        try {
            // Получаем объект person из Optional
            Person person = service.getPersonById(id).orElseThrow(()
                    -> new EntityNotFoundException("Машина не найдена с id: " + id));
            return ResponseEntity.ok(person);  // Возвращаем статус 200 OK и объект автомобиля
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());  // Если автомобиль не найден
        } catch (Exception e) {
            log.error("Ошибка при получении person", e);  // Логируем общую ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");  // Общая ошибка
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable Integer id) {
        try {
            service.deletePersonById(id);
            return ResponseEntity.ok().build();  // Возвращаем статус 200 OK без тела ответа
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Person с ID " + id + " не найдена"); // Возвращаем статус 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при удалении машины"); // Обработка других ошибок
        }
    }
}
