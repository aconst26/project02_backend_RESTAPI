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
        user1 = new User("test", "test@gmail.com", "test");
        user2 = new User("admin", "admin@gmail.com", "admin");
        user3 = new User("ac", "ac@gmail.com", "ac");
    }

    @Test
    @DisplayName("Default constructor should create empty user")
    void testDefaultConstructor() {
        User emptyUser = new User();

        assertNotNull(emptyUser);
        assertEquals(0, emptyUser.getId());
        assertNull(emptyUser.getUsername());
        assertNull(emptyUser.getEmail());
        assertNull(emptyUser.getPassword());
    }

    @Test
    @DisplayName("Parameterized constructor should set username, email and password")
    void testParameterizedConstructor() {
        User user = new User("Test Username", "Test Email", "Test Password");

        assertNotNull(user);
        assertEquals("Test Username", user.getUsername());
        assertEquals("Test Email", user.getEmail());
        assertEquals("Test Password", user.getPassword());
        assertEquals(0, user.getId()); // ID not set by constructor
    }

    @Test
    @DisplayName("Setters and getters should work correctly")
    void testSettersAndGetters() {
        User user = new User();

        user.setId(1L);
        user.setUsername("New Username");
        user.setEmail("New Email");
        user.setPassword("New Password");

        assertEquals(1L, user.getId());
        assertEquals("New Username", user.getUsername());
        assertEquals("New Email", user.getEmail());
        assertEquals("New Password", user.getPassword());
    }

    @Test
    @DisplayName("equals() should return true for books with same content")
    void testEqualsWithSameContent() {
        user1.setId(1L);
        user2.setId(1L);

        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));
    }

    @Test
    @DisplayName("equals() should return false for books with different content")
    void testEqualsWithDifferentContent() {
        user1.setId(1L);
        user3.setId(1L);

        assertFalse(user1.equals(user3));
        assertFalse(user3.equals(user1));
    }

    @Test
    @DisplayName("equals() should return false for books with different IDs")
    void testEqualsWithDifferentIds() {
        user1.setId(1L);
        user2.setId(2L);

        assertFalse(user1.equals(user2));
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
        user1.setId(1L);
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("hashCode() should be equal for equal objects")
    void testHashCodeEqualityForEqualObjects() {
        user1.setId(1L);
        user2.setId(1L);

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    @DisplayName("toString() should contain all user information")
    void testToString() {
        user1.setId(1L);
        String toString = user1.toString();

        assertTrue(toString.contains("User{"));
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("username='test'"));
        assertTrue(toString.contains("email='test@gmail.com'"));
        assertTrue(toString.contains("password='test'"));
    }

    @Test
    @DisplayName("toString() should handle null values gracefully")
    void testToStringWithNullValues() {
        User user = new User();
        user.setId(1L);
        String toString = user.toString();

        assertTrue(toString.contains("User{"));
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("username='null'"));
        assertTrue(toString.contains("email='null'"));
        assertTrue(toString.contains("password='null'"));
    }
}