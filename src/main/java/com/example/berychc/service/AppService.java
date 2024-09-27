package com.example.berychc.service;

import com.example.berychc.entity.Application;
import com.example.berychc.entity.MyUser;
import com.example.berychc.repository.MyUserRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AppService {

    private List<Application> applications;

    private MyUserRepository repository;

    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadAppInDb() {
        Faker faker = new Faker();
         applications = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                 .toList();
    }

    public List<Application> allApplications() {
        return applications;
    }

    public Application getApplicationById(Integer id) {
        return applications.stream()
                .filter(app -> app.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}
