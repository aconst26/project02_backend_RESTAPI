package com.example.sportsbetting;

/**
 * Custom exception to handle cases where a team is not found in the database.
 */
public class TeamNotFoundException extends RuntimeException {

    // Constructor that accepts a team ID and generates a custom error message
    TeamNotFoundException(Long id) {
        super("Could not find team: " + id);
    }
}