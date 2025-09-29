package com.example.sportsbetting;

import com.example.sportsbetting.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User Repository
 *
 * @author Anthony Constante
 * @version 0.1.0
 * @since 9/29/25
 */

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(String username);
}
