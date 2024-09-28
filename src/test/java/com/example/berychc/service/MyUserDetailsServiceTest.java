package com.example.berychc.service;

import com.example.berychc.entity.MyUser;
import com.example.berychc.repository.MyUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MyUserDetailsServiceTest {

    @Mock
    private MyUserRepository repository;

    @InjectMocks
    private MyUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUsername_UserFound_ShouldReturnUserDetails() {
        // Подготовка тестовых данных
        String username = "testuser";
        MyUser myUser = new MyUser(); // Создай экземпляр MyUser с необходимыми данными
        myUser.setName(username);

        // Настройка мока
        when(repository.findByName(username)).thenReturn(Optional.of(myUser));

        // Вызов метода
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Проверка результатов
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    public void loadUserByUsername_UserNotFound_ShouldThrowException() {
        // Подготовка тестовых данных
        String username = "nonexistentuser";

        // Настройка мока
        when(repository.findByName(username)).thenReturn(Optional.empty());

        // Проверка на выброс исключения
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(username);
        });
    }
}