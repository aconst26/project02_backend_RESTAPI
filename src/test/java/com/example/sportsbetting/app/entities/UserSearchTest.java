package com.example.sportsbetting.app.repositories;

import com.example.sportsbetting.entities.User;
import com.example.sportsbetting.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("User Search Tests")
@ExtendWith(MockitoExtension.class)
class UserSearchTest {

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Search User by ID")
    void testSearchUserById() {
        User user = new User(1, "testUser", "password", "test@example.com");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findById(1);

        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUserName());
    }

    @Test
    @DisplayName("Search User by Username")
    void testSearchUserByUsername() {
        User user = new User(1, "testUser", "password", "test@example.com");
        when(userRepository.getUserByUserName("testUser")).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.getUserByUserName("testUser");

        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUserName());
    }
}