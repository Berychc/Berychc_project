package com.example.berychc.controller;

import com.example.berychc.entity.Cars;
import com.example.berychc.repository.CarsRepository;
import com.example.berychc.service.CarsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarsController.class)
public class CarsControllerMvcTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private CarsRepository repository;

    @SpyBean
    private CarsService service;

    @InjectMocks
    private CarsController controller;

    @Test
    @DisplayName("Проверка контроллера не null")
    void CarControllerTest() {
        Assertions.assertNotNull(controller);
    }

    @Test
    @DisplayName("Тест метода createOrUpdate")
    void testCreateOrUpdateCarTest() throws Exception {
        Cars car = new Cars();
        car.setId(1);
        car.setBrand("Bmw");
        car.setSeries("1-es");
        car.setChassisNumber("E87");
        car.setHorsePower((short) 115);

        when(service.createOrUpdateCar(car)).thenReturn(car); // Настройка мока

        mock.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"brand\":\"Bmw\",\"series\":\"1-es\",\"chassisNumber\":\"E87\",\"horsePower\":115}")) // JSON представление машины
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("Bmw"))
                .andExpect(jsonPath("$.series").value("1-es"))
                .andExpect(jsonPath("$.chassisNumber").value("E87"))
                .andExpect(jsonPath("$.horsePower").value(115));
    }


    @Test
    @DisplayName("Тест метода createOrUpdate с NOT_FOUND")
    void testCreateOrUpdateCarNotFound() throws Exception {
        Cars car = new Cars();
        car.setId(1);
        car.setBrand("Bmw");
        car.setSeries("1-es");
        car.setChassisNumber("E87");
        car.setHorsePower((short) 115);

        // Имитация исключения, когда автомобиль не найден
        doThrow(new EntityNotFoundException()).when(service).createOrUpdateCar(car);

        mock.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"brand\":\"Bmw\",\"series\":\"1-es\",\"chassisNumber\":\"E87\",\"horsePower\":115}"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Cars(id=1, brand=Bmw, series=1-es, chassisNumber=E87, horsePower=115)Не найдена"));
    }

    @Test
    @DisplayName("Тест метода createOrUpdate - внутренняя ошибка")
    void testCreateOrUpdateCarInternalError() throws Exception {
        Cars car = new Cars();
        car.setId(1);
        car.setBrand("Bmw");
        car.setSeries("1-es");
        car.setChassisNumber("E87");
        car.setHorsePower((short) 115);

        // Имитация внутренней ошибки
        doThrow(new RuntimeException()).when(service).createOrUpdateCar(car);

        mock.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"brand\":\"Bmw\",\"series\":\"1-es\",\"chassisNumber\":\"E87\",\"horsePower\":115}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Произошла ошибка"));  // Проверка сообщения об ошибке
    }
}
