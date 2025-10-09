package com.example.sportsbetting;

/**
 * Custom exception to handle cases where a user is not found in the database.
 */
public class UserNotFoundException extends RuntimeException {

    // Constructor that accepts a user ID and generates a custom error message
    UserNotFoundException(Long id) {
        super("Could not find user: " + id);
    }
}