package com.example.sportsbetting;

/**
 * Custom exception to handle cases where a stat is not found in the database.
 */
public class StatNotFoundException extends RuntimeException {

    // Constructor that accepts a stat ID and generates a custom error message
    StatNotFoundException(Long id) {
        super("Could not find stat: " + id);
    }
}