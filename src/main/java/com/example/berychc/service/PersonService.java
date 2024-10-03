package com.example.berychc.service;

import com.example.berychc.entity.Person;
import com.example.berychc.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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
    public Person getPersonById(Integer id) {
        log.info("Вызван метод - getPersonById");
        List<Person> people = repository.findAll(); // Предположим, что findAll() возвращает список всех Person
        return people.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Person with id " + id + " not found"));
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
