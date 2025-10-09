package com.example.sportsbetting;

import com.example.sportsbetting.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User Repository
 * 
 * This interface provides methods for interacting with the User database table.
 * It extends JpaRepository to inherit standard CRUD operations.
 * Additional methods are defined for fetching users by username and email.
 * 
 * @author Anthony Constante
 * @version 0.1.0
 * @since 9/29/25
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    // Fetch a user by their username
    Optional<User> getUserByUserName(String userName);

    // Fetch a user by their email
    Optional<User> getUserByEmail(String email);
}