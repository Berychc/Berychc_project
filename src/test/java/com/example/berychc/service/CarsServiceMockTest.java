package com.example.berychc.service;

import com.example.berychc.entity.Cars;
import com.example.berychc.repository.CarsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarsServiceMockTest {

    @Mock
    private CarsRepository repository;

    @InjectMocks
    private CarsService service;

    @Test
    @DisplayName("Тест метода Create и Update")
    void createOrUpdateCarsTest() {
        Cars expected = new Cars(1, "Bmw", "1-es", "E87", (short) 115);
        when(repository.save(expected)).thenReturn(expected);

        Cars actual = service.createOrUpdateCar(expected);

        verify(repository, only()).save(expected);
        Assertions.assertEquals(expected, actual);


        Cars actualBrand = service.createOrUpdateCar(expected);
        actualBrand.setBrand("Toyota");

        Assertions.assertEquals(expected, actualBrand);
    }

    @Test
    @DisplayName("Тест метод GetById")
    void getCarByIdTest() {
        Cars expected = new Cars(1, "Bmw", "1-es", "E87", (short) 115);
        when(repository.save(expected)).thenReturn(expected);

        Cars actual = service.createOrUpdateCar(expected);

        verify(repository, only()).save(expected);
        Assertions.assertEquals(expected, actual);

        when(repository.findById(1)).thenReturn(Optional.of(expected));

        Optional<Cars> actual1 = service.getCarById(1);

        Assertions.assertTrue(actual1.isPresent());
    }

    @Test
    @DisplayName("Я тупо хуй")
    void deleteByIdTest() {
        Cars expected = new Cars(1, "Bmw", "1-es", "E87", (short) 115);
        when(repository.save(expected)).thenReturn(expected);

        Cars actual = service.createOrUpdateCar(expected);

        verify(repository, only()).save(expected);
        Assertions.assertEquals(expected, actual);

        when(repository.findById(1)).thenReturn(Optional.of(actual));

        Optional<Cars> actual1 = service.getCarById(1);

        service.deleteCarById(1);

        Assertions.assertTrue(actual1.isPresent());
    }
}
