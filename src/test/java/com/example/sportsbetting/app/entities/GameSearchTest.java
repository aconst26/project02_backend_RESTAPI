package com.example.sportsbetting.app.entities;

import com.example.sportsbetting.entities.Game;
import com.example.sportsbetting.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
        import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Game Search Tests")
@ExtendWith(MockitoExtension.class)
class GameSearchTest {

    @Mock
    private GameRepository gameRepository;

    @Test
    @DisplayName("Search Game by Date")
    void testSearchGameByDate() {
        Game game = new Game(LocalDate.of(2025, 11, 10), 1, "Cleveland Cavaliers", 2, "Denver Nuggets");
        when(gameRepository.findByDate(LocalDate.of(2025, 11, 10))).thenReturn(List.of(game));

        List<Game> result = gameRepository.findByDate(LocalDate.of(2025, 11, 10));

        assertFalse(result.isEmpty());
        assertEquals("Cleveland Cavaliers", result.get(0).getHometeamName());
    }
}