package com.example.sportsbetting;

/**
 * Custom exception to handle cases where a favorite is not found in the database.
 */
public class FavNotFoundException extends RuntimeException {

    // Constructor that accepts a favorite ID and generates a custom error message
    FavNotFoundException(Long id) {
        super("Could not find favorite: " + id);
    }
}