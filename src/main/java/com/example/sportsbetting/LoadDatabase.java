package com.example.sportsbetting;

import com.example.sportsbetting.entities.User;
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

    @Bean // Define a CommandLineRunner bean
    public CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            // Preload test and admin users
            preloadUser(repository, "test", "test@gmail.com", "test");
            preloadUser(repository, "admin", "admin@gmail.com", "admin");
        };
    }

    // Helper method to preload a user if it doesn't already exist
    private void preloadUser(UserRepository repository, String userName, String email, String userPassword) {
        boolean userNameExists = repository.getUserByUserName(userName).isPresent();
        boolean emailExists = repository.getUserByEmail(email).isPresent();

        if (!userNameExists && !emailExists) {
            User user = new User(userName, userPassword, email);
            repository.save(user);
            log.info("Preloaded user: " + userName + " (" + email + ")");
        } else {
            log.info("Skipping preload. Existing user conflict: "
                    + (userNameExists ? "userName" : "")
                    + (userNameExists && emailExists ? " & " : "")
                    + (emailExists ? "email" : ""));
        }
    }
}