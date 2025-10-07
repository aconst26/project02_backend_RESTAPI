package com.example.sportsbetting.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Entity class representing a User in the database.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    private Integer userID; // Changed from id to userID and removed auto-generation
    private String userName; // Username of the user
    private String UserPassword; // Password of the user (capitalized to match DB)
    private String email; // Email address of the user

    // No-argument constructor (required by JPA)
    public User() {
    }

    // Constructor with parameters
    public User(String userName, String UserPassword, String email) {
        this.userName = userName;
        this.UserPassword = UserPassword;
        this.email = email;
    }

    // Constructor with ID for preloading
    public User(Integer userID, String userName, String UserPassword, String email) {
        this.userID = userID;
        this.userName = userName;
        this.UserPassword = UserPassword;
        this.email = email;
    }

    // Getters and setters
    public Integer getUserID() { // Changed from getId to getUserID
        return userID;
    }

    public void setUserID(Integer userID) { // Changed from setId to setUserID
        this.userID = userID;
    }

    // Keep old methods for backward compatibility
    public Integer getId() {
        return userID;
    }

    public void setId(Integer id) {
        this.userID = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        this.UserPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Override equals() to compare users based on their fields
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(UserPassword, user.UserPassword) &&
                Objects.equals(email, user.email);
    }

    // Override hashCode() for consistent hashing
    @Override
    public int hashCode() {
        return Objects.hash(userName, UserPassword, email);
    }

    // Override toString() for a readable representation of the user
    @Override
    public String toString() {
        return "User{" +
            "id=" + userID +
            ", userName='" + userName + '\'' +
            ", userPassword='" + UserPassword + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}