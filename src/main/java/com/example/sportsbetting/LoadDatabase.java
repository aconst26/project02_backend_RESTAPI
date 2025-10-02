package com.example.sportsbetting;

import com.example.sportsbetting.entities.User;
import com.example.sportsbetting.entities.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for preloading the database with initial data.
 */
@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, TeamRepository teamRepository) {
        return args -> {
            // Preload users with exact IDs from your database
            preloadUsers(userRepository);
            
            // Preload teams with exact data from your database
            preloadTeams(teamRepository);
        };
    }

    // Helper method to preload users with exact data from your database
    private void preloadUsers(UserRepository repository) {
        // Check if users already exist to avoid duplicates
        if (repository.count() == 0) {
            // Users from your database script
            repository.save(new User(1, "test", "test", "test@gmail.com"));
            repository.save(new User(2, "admin", "admin", "admin@gmail.com"));
            
            log.info("Preloaded 2 users from database script");
        } else {
            log.info("Users already exist, skipping preload");
        }
    }

    // Helper method to preload teams with exact data from your database
    private void preloadTeams(TeamRepository repository) {
        // Check if our specific NBA teams exist
        if (!repository.existsById(1) || !repository.findById(1).get().getName().equals("Cleveland Cavaliers")) {
            // Clear and reload with NBA teams
            repository.deleteAll();
            
            repository.save(new Team(1, "Cleveland Cavaliers", "Central", "Eastern"));
            repository.save(new Team(2, "Denver Nuggets", "Northwest", "Western"));
            repository.save(new Team(3, "Houston Rockets", "Southwest", "Western"));
            repository.save(new Team(4, "Oklahoma City Thunder", "Northwest", "Western"));
            repository.save(new Team(5, "Memphis Grizzlies", "Southwest", "Western"));
            repository.save(new Team(10, "Golden State Warriors", "Pacific", "Western"));
            
            log.info("Loaded NBA teams, replacing any existing teams");
        } else {
            log.info("NBA teams already exist, skipping preload");
        }
    }
}