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
        game1 = new Game(LocalDate.of(2025, 11, 10), 1, 2);
        game2 = new Game(LocalDate.of(2025, 12, 5), 10, 3);
        game3 = new Game(LocalDate.of(2026, 1, 15), 4, 5);
    }

    @Test
    @DisplayName("Default constructor should create empty game")
    void testDefaultConstructor() {
        Game emptyGame = new Game();

        assertNotNull(emptyGame);
        assertNull(emptyGame.getGameID());
        assertNull(emptyGame.getDate());
        assertNull(emptyGame.getHometeamID());
        assertNull(emptyGame.getAwayteamID());
    }

    @Test
    @DisplayName("Parameterized constructor should set date, hometeamID, and awayteamID")
    void testParameterizedConstructor() {
        Game game = new Game(LocalDate.of(2025, 11, 15), 5, 8);

        assertNotNull(game);
        assertEquals(LocalDate.of(2025, 11, 15), game.getDate());
        assertEquals(5, game.getHometeamID());
        assertEquals(8, game.getAwayteamID());
        assertNull(game.getGameID());
    }

    @Test
    @DisplayName("Constructor with ID should set all fields")
    void testParameterizedConstructorWithId() {
        Game game = new Game(1, LocalDate.of(2025, 11, 15), 5, 8);

        assertNotNull(game);
        assertEquals(1, game.getGameID());
        assertEquals(LocalDate.of(2025, 11, 15), game.getDate());
        assertEquals(5, game.getHometeamID());
        assertEquals(8, game.getAwayteamID());
    }

    @Test
    @DisplayName("Setters and getters should work correctly")
    void testSettersAndGetters() {
        Game game = new Game();

        game.setGameID(1);
        game.setDate(LocalDate.of(2025, 12, 25));
        game.setHometeamID(7);
        game.setAwayteamID(9);

        assertEquals(1, game.getGameID());
        assertEquals(LocalDate.of(2025, 12, 25), game.getDate());
        assertEquals(7, game.getHometeamID());
        assertEquals(9, game.getAwayteamID());
    }

    @Test
    @DisplayName("equals() should return true for games with same content")
    void testEqualsWithSameContent() {
        Game gameA = new Game(LocalDate.of(2025, 11, 10), 1, 2);
        Game gameB = new Game(LocalDate.of(2025, 11, 10), 1, 2);

        assertTrue(gameA.equals(gameB));
        assertTrue(gameB.equals(gameA));
    }

    @Test
    @DisplayName("equals() should return false for games with different content")
    void testEqualsWithDifferentContent() {
        assertFalse(game1.equals(game2));
        assertFalse(game2.equals(game1));
    }

    @Test
    @DisplayName("equals() should return false when compared to null")
    void testEqualsWithNull() {
        assertFalse(game1.equals(null));
    }

    @Test
    @DisplayName("equals() should return false when compared to different type")
    void testEqualsWithDifferentType() {
        assertFalse(game1.equals("Not a game"));
    }

    @Test
    @DisplayName("equals() should return true when object is compared to itself")
    void testEqualsWithSameObject() {
        assertTrue(game1.equals(game1));
    }

    @Test
    @DisplayName("hashCode() should be consistent")
    void testHashCodeConsistency() {
        int hashCode1 = game1.hashCode();
        int hashCode2 = game1.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("hashCode() should be equal for equal objects")
    void testHashCodeEqualityForEqualObjects() {
        Game gameA = new Game(LocalDate.of(2025, 11, 10), 1, 2);
        Game gameB = new Game(LocalDate.of(2025, 11, 10), 1, 2);

        assertEquals(gameA.hashCode(), gameB.hashCode());
    }

    @Test
    @DisplayName("toString() should contain all game information")
    void testToString() {
        game1.setGameID(1);
        String toString = game1.toString();

        assertTrue(toString.contains("Game{"));
        assertTrue(toString.contains("gameID=1"));
        assertTrue(toString.contains("date=2025-11-10"));
        assertTrue(toString.contains("hometeamID=1"));
        assertTrue(toString.contains("awayteamID=2"));
    }

    @Test
    @DisplayName("toString() should handle null values gracefully")
    void testToStringWithNullValues() {
        Game game = new Game();
        game.setGameID(1);
        String toString = game.toString();

        assertTrue(toString.contains("Game{"));
        assertTrue(toString.contains("gameID=1"));
        assertTrue(toString.contains("date=null"));
        assertTrue(toString.contains("hometeamID=null"));
        assertTrue(toString.contains("awayteamID=null"));
    }

    @Test
    @DisplayName("Should add and delete a game")
    void testAddAndDeleteGame() {
        Game newGame = new Game(LocalDate.of(2025, 12, 31), 6, 7);

        // Simulate saving the game
        when(gameRepository.save(newGame)).thenReturn(newGame);
        Game savedGame = gameRepository.save(newGame);

        assertNotNull(savedGame);
        assertEquals(LocalDate.of(2025, 12, 31), savedGame.getDate());
        assertEquals(6, savedGame.getHometeamID());
        assertEquals(7, savedGame.getAwayteamID());

        // Simulate deleting the game
        doNothing().when(gameRepository).deleteById(savedGame.getGameID());
        gameRepository.deleteById(savedGame.getGameID());

        verify(gameRepository, times(1)).deleteById(savedGame.getGameID());
    }
}