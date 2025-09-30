package com.example.sportsbetting;

import com.example.sportsbetting.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            preloadUser(repository, "test", "test@gmail.com", "test");
            preloadUser(repository, "admin", "admin@gmail.com", "admin");
        };
    }

    private void preloadUser(UserRepository repository, String username, String email, String password) {
        boolean usernameExists = repository.getUserByUsername(username).isPresent();
        boolean emailExists = repository.getUserByEmail(email).isPresent();

        if (!usernameExists && !emailExists) {
            User user = new User(username, email, password);
            repository.save(user);
            log.info("Preloaded user: " + username + " (" + email + ")");
        } else {
            log.info("Skipping preload. Existing user conflict: "
                    + (usernameExists ? "username" : "")
                    + (usernameExists && emailExists ? " & " : "")
                    + (emailExists ? "email" : ""));
        }
    }
}
