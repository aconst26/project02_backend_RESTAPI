package com.example.sportsbetting;

import com.example.sportsbetting.entities.User;
import com.example.sportsbetting.entities.Team;
import com.example.sportsbetting.entities.Fav;
import com.example.sportsbetting.entities.Game;
import com.example.sportsbetting.entities.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Configuration class for preloading the database with initial data.
 */
@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, TeamRepository teamRepository,
            StatRepository statRepository, GameRepository gameRepository, FavRepository favRepository) {
        return args -> {
            // Preload users with exact IDs from your database
            preloadUsers(userRepository);

            // Preload teams with exact data from your database
            preloadTeams(teamRepository);

            // Preload stats with exact data from your database
            preloadStats(statRepository);

            // Preload games with exact data from your database
            preloadGames(gameRepository);

            // Preload favorites with exact data from your database
            preloadFavs(favRepository);
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

    // Helper method to preload stats with exact data from your database
    private void preloadStats(StatRepository repository) {
        // Check if stats already exist to avoid duplicates
        if (repository.count() == 0) {
            // Stats from your database script
            repository.save(new Stat(1, 1, new BigDecimal("36.5"), new BigDecimal("48.1"), 13, 25, 44,
                    new BigDecimal("0.585")));
            repository.save(new Stat(2, 2, new BigDecimal("38.0"), new BigDecimal("49.2"), 12, 27, 46,
                    new BigDecimal("0.610")));
            repository.save(new Stat(3, 3, new BigDecimal("34.1"), new BigDecimal("46.7"), 15, 23, 42,
                    new BigDecimal("0.420")));
            repository.save(new Stat(4, 4, new BigDecimal("37.9"), new BigDecimal("47.9"), 14, 26, 45,
                    new BigDecimal("0.570")));
            repository.save(new Stat(5, 5, new BigDecimal("35.4"), new BigDecimal("45.8"), 14, 24, 43,
                    new BigDecimal("0.410")));
            repository.save(new Stat(6, 10, new BigDecimal("38.5"), new BigDecimal("48.3"), 13, 29, 41,
                    new BigDecimal("0.520")));

            log.info("Preloaded 6 team stats from database script");
        } else {
            log.info("Stats already exist, skipping preload");
        }
    }

    private void preloadGames(GameRepository repository) {
        // Check if games already exist to avoid duplicates
        if (repository.count() == 0) {
            // Games from your database script
            repository.save(new Game(LocalDate.of(2025, 11, 10), 1, 2)); // Cavaliers vs Nuggets
            repository.save(new Game(LocalDate.of(2025, 12, 5), 10, 3)); // Warriors vs Rockets
            repository.save(new Game(LocalDate.of(2026, 1, 15), 4, 5)); // Thunder vs Grizzlies
            repository.save(new Game(LocalDate.of(2026, 2, 22), 2, 10)); // Nuggets vs Warriors
            repository.save(new Game(LocalDate.of(2026, 3, 30), 3, 1)); // Rockets vs Cavaliers

            log.info("Preloaded 5 games from database script");
        } else {
            log.info("Games already exist, skipping preload");
        }
    }

    private void preloadFavs(FavRepository repository) {
        // Check if favorites already exist to avoid duplicates
        if (repository.count() == 0) {
            // Favorites from your database script
            repository.save(new Fav(1, 1, 10)); // test user likes Warriors

            log.info("Preloaded 1 favorite from database script");
        } else {
            log.info("Favorites already exist, skipping preload");
        }
    }
}