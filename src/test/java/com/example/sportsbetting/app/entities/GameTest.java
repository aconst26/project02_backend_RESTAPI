package com.example.sportsbetting.app.entities;

import com.example.sportsbetting.entities.Game;
import com.example.sportsbetting.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Game Entity Tests")
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class GameTest {

    private Game game1;
    private Game game2;
    private Game game3;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        game1 = new Game(LocalDate.of(2025, 11, 10), 1, "Cleveland Cavaliers", 2, "Denver Nuggets");
        game2 = new Game(LocalDate.of(2025, 12, 5), 10, "Golden State Warriors", 3, "Houston Rockets");
        game3 = new Game(LocalDate.of(2026, 1, 15), 4, "Oklahoma City Thunder", 5, "Memphis Grizzlies");
    }

    @Test
    @DisplayName("Parameterized constructor should set all fields")
    void testParameterizedConstructor() {
        Game game = new Game(LocalDate.of(2025, 11, 15), 5, "Test Home Team", 8, "Test Away Team");

        assertNotNull(game);
        assertEquals(LocalDate.of(2025, 11, 15), game.getDate());
        assertEquals(5, game.getHometeamID());
        assertEquals("Test Home Team", game.getHometeamName());
        assertEquals(8, game.getAwayteamID());
        assertEquals("Test Away Team", game.getAwayteamName());
    }
}