package com.example.berychc.service;

import com.example.berychc.entity.Person;
import com.example.berychc.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {
    
    private final PersonRepository repository;

    /**
     * Метод создания и обновления сущности
     * @param person
     * @return new car or update car
     */
    public Person createOrUpdatePerson(Person person) {
        log.info("Вызван метод - createOrUpdatePerson");
        return repository.save(person);
    }

    /**
     * Метод поиска сущности по id
     * @param id
     * @return id
     */
    public Optional<Person> getPersonById(Integer id) {
        log.info("Вызван  метод - getCarById");
        return repository.findById(id);
    }

    /**
     * Метод удаления сущности
     * @param id
     */
    public void deletePersonById(Integer id) {
        log.info("Вызван метод - deletePerson");
        repository.deleteById(id);
    }
}
