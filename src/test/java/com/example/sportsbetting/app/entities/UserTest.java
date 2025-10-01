package com.example.sportsbetting.app.entities;

import com.example.sportsbetting.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Entity Tests")
class UserTest {

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        user1 = new User("test", "test", "test@gmail.com");
        user2 = new User("admin", "admin", "admin@gmail.com");
        user3 = new User("ac", "ac", "ac@gmail.com");
    }

    @Test
    @DisplayName("Default constructor should create empty user")
    void testDefaultConstructor() {
        User emptyUser = new User();

        assertNotNull(emptyUser);
        assertNull(emptyUser.getId());
        assertNull(emptyUser.getUserName());
        assertNull(emptyUser.getEmail());
        assertNull(emptyUser.getUserPassword());
    }

    @Test
    @DisplayName("Parameterized constructor should set username, email, and password")
    void testParameterizedConstructor() {
        User user = new User("Test Username", "Test Password", "Test Email");

        assertNotNull(user);
        assertEquals("Test Username", user.getUserName());
        assertEquals("Test Password", user.getUserPassword());
        assertEquals("Test Email", user.getEmail());
        assertNull(user.getId());
    }

    @Test
    @DisplayName("Setters and getters should work correctly")
    void testSettersAndGetters() {
        User user = new User();

        user.setId(1);
        user.setUserName("New Username");
        user.setEmail("New Email");
        user.setUserPassword("New Password");

        assertEquals(1, user.getId());
        assertEquals("New Username", user.getUserName());
        assertEquals("New Email", user.getEmail());
        assertEquals("New Password", user.getUserPassword());
    }

    @Test
    @DisplayName("equals() should return true for users with same content")
    void testEqualsWithSameContent() {
        User userA = new User("test", "test", "test@gmail.com");
        User userB = new User("test", "test", "test@gmail.com");

        assertTrue(userA.equals(userB));
        assertTrue(userB.equals(userA));
    }

    @Test
    @DisplayName("equals() should return false for users with different content")
    void testEqualsWithDifferentContent() {
        assertFalse(user1.equals(user3));
        assertFalse(user3.equals(user1));
    }

    @Test
    @DisplayName("equals() should return false when compared to null")
    void testEqualsWithNull() {
        assertFalse(user1.equals(null));
    }

    @Test
    @DisplayName("equals() should return false when compared to different type")
    void testEqualsWithDifferentType() {
        assertFalse(user1.equals("Not a user"));
    }

    @Test
    @DisplayName("equals() should return true when object is compared to itself")
    void testEqualsWithSameObject() {
        assertTrue(user1.equals(user1));
    }

    @Test
    @DisplayName("hashCode() should be consistent")
    void testHashCodeConsistency() {
        int hashCode1 = user1.hashCode();
        int hashCode2 = user1.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("hashCode() should be equal for equal objects")
    void testHashCodeEqualityForEqualObjects() {
        User userA = new User("test", "test", "test@gmail.com");
        User userB = new User("test", "test", "test@gmail.com");

        assertEquals(userA.hashCode(), userB.hashCode());
    }

    @Test
    @DisplayName("toString() should contain all user information")
    void testToString() {
        user1.setId(1);
        String toString = user1.toString();

        assertTrue(toString.contains("User{"));
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("userName='test'"));
        assertTrue(toString.contains("email='test@gmail.com'"));
        assertTrue(toString.contains("userPassword='test'"));
    }

    @Test
    @DisplayName("toString() should handle null values gracefully")
    void testToStringWithNullValues() {
        User user = new User();
        user.setId(1);
        String toString = user.toString();

        assertTrue(toString.contains("User{"));
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("userName='null'"));
        assertTrue(toString.contains("email='null'"));
        assertTrue(toString.contains("userPassword='null'"));
    }
}