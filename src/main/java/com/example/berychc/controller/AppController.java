package com.example.berychc.controller;

import com.example.berychc.entity.Application;
import com.example.berychc.entity.MyUser;
import com.example.berychc.service.AppService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app")
@AllArgsConstructor
public class AppController {

    private AppService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to page";
    }

    @GetMapping("/all-app")
    public List<Application> allApplication() {
        return service.allApplications();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application application(@PathVariable Integer id) {
        return service.getApplicationById(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUser user) {
        service.addUser(user);
        return "User is saved!";
    }
}
