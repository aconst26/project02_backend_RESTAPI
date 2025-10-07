package com.example.sportsbetting;

/**
 * Custom exception to handle cases where a game is not found in the database.
 */
public class GameNotFoundException extends RuntimeException {

    // Constructor that accepts a game ID and generates a custom error message
    GameNotFoundException(Long id) {
        super("Could not find game: " + id);
    }
}