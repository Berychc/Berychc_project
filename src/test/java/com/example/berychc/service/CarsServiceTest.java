package com.example.berychc.service;

import com.example.berychc.entity.Cars;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CarsServiceTest {

    @Autowired
    private CarsService service;

    @Test
    @DisplayName("Тест метода Create и Update")
    void createOrUpdateTest() {
        // Создаем экземпляр машины
        Cars cars = new Cars(1, "Bmw", "1-es", "E87", (short) 115);

        // Сохраняем машину
        Cars savedCar = service.createOrUpdateCar(cars);

        // Проверяем есть ли она
        Assertions.assertNotNull(savedCar, "Сохраненная машина не должна быть null");

        //  Изменяем параметры
        cars.setBrand("Toyota");

        // Проверяем параметры на верность
        Assertions.assertEquals("Toyota", cars.getBrand(), "Изменения должны совпадать");
    }

    @Test
    @DisplayName("Тест метода GetById")
    void GetCarsByIdTest() {
        // Создаем экземпляр машины
        Cars cars = new Cars(1, "Bmw", "1-es", "E87", (short) 115);

        // Сохраняем машину
        Cars savedCar = service.createOrUpdateCar(cars);
        Assertions.assertNotNull(savedCar, "Сохраненная машина не должна быть null");

        // Получаем машину по ID
        Optional<Cars> actual = service.getCarById(1);

        // Проверяем, что объект Optional не пустой
        Assertions.assertTrue(actual.isPresent(), "Полученная машина должна быть present");

        // Проверяем, что полученный объект совпадает с сохраненным автомобилем
        Assertions.assertEquals(cars, actual.get(), "Полученная машина должна совпадать с сохраненной");
    }

    @Test
    @DisplayName("Тест метода DeleteById")
    void deleteByIdTest() {
        // Создаем экземпляр машины
        Cars cars = new Cars(1, "Bmw", "1-es", "E87", (short) 115);

        // Сохраняем машину
        Cars savedCar = service.createOrUpdateCar(cars);
        Assertions.assertNotNull(savedCar, "Сохраненная машина не должна быть null");

        // Удаляем машину по ID
        service.deleteCarById(1);

        // Проверяем, что машина была успешно удалена
        Optional<Cars> deletedCar = service.getCarById(1);
        Assertions.assertFalse(deletedCar.isPresent(), "Машина с ID 1 должна быть отсутствовать после удаления");
    }
}