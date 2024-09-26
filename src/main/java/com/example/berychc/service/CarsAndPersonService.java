package com.example.berychc.service;

import com.example.berychc.entity.Cars;
import com.example.berychc.entity.Person;
import com.example.berychc.repository.CarsRepository;
import com.example.berychc.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarsAndPersonService {

    private final CarsRepository repository;
    private final PersonRepository personRepository;

    public Cars createOrUpdateCar(Cars car, Integer personId) {
        log.info("Вызван метод - createOrUpdateCar");

        if (personId != null) {
            Optional<Person> optionalPerson = personRepository.findById(personId);
            if (optionalPerson.isPresent()) {
                car.setPerson(optionalPerson.get());
            } else {
                log.warn("Person with id {} not found", personId);
                throw new EntityNotFoundException("Person not found with id: " + personId);
            }
        }
        return repository.save(car);
    }
}
