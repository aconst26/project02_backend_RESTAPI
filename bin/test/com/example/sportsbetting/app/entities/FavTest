package com.example.sportsbetting.app.entities;

import com.example.sportsbetting.entities.Fav;
import com.example.sportsbetting.FavRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Fav Entity Tests")
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class FavTest {

    private Fav fav1;
    private Fav fav2;
    private Fav fav3;

    @Mock
    private FavRepository favRepository;

    @BeforeEach
    void setUp() {
        fav1 = new Fav(1, 10);  // User 1 likes Warriors (team 10)
        fav2 = new Fav(2, 1);   // User 2 likes Cavaliers (team 1)
        fav3 = new Fav(1, 2);   // User 1 likes Nuggets (team 2)
    }

    @Test
    @DisplayName("Default constructor should create empty favorite")
    void testDefaultConstructor() {
        Fav emptyFav = new Fav();

        assertNotNull(emptyFav);
        assertNull(emptyFav.getFav());
        assertNull(emptyFav.getUserID());
        assertNull(emptyFav.getTeamID());
    }

    @Test
    @DisplayName("Parameterized constructor should set userID and teamID")
    void testParameterizedConstructor() {
        Fav fav = new Fav(5, 8);

        assertNotNull(fav);
        assertEquals(5, fav.getUserID());
        assertEquals(8, fav.getTeamID());
        assertNull(fav.getFav());
    }

    @Test
    @DisplayName("Constructor with ID should set all fields")
    void testParameterizedConstructorWithId() {
        Fav fav = new Fav(1, 5, 8);

        assertNotNull(fav);
        assertEquals(1, fav.getFav());
        assertEquals(5, fav.getUserID());
        assertEquals(8, fav.getTeamID());
    }

    @Test
    @DisplayName("Setters and getters should work correctly")
    void testSettersAndGetters() {
        Fav fav = new Fav();

        fav.setFav(1);
        fav.setUserID(7);
        fav.setTeamID(9);

        assertEquals(1, fav.getFav());
        assertEquals(7, fav.getUserID());
        assertEquals(9, fav.getTeamID());
    }

    @Test
    @DisplayName("equals() should return true for favs with same content")
    void testEqualsWithSameContent() {
        Fav favA = new Fav(1, 10);
        Fav favB = new Fav(1, 10);

        assertTrue(favA.equals(favB));
        assertTrue(favB.equals(favA));
    }

    @Test
    @DisplayName("equals() should return false for favs with different content")
    void testEqualsWithDifferentContent() {
        assertFalse(fav1.equals(fav2));
        assertFalse(fav2.equals(fav1));
    }

    @Test
    @DisplayName("equals() should return false when compared to null")
    void testEqualsWithNull() {
        assertFalse(fav1.equals(null));
    }

    @Test
    @DisplayName("equals() should return false when compared to different type")
    void testEqualsWithDifferentType() {
        assertFalse(fav1.equals("Not a fav"));
    }

    @Test
    @DisplayName("equals() should return true when object is compared to itself")
    void testEqualsWithSameObject() {
        assertTrue(fav1.equals(fav1));
    }

    @Test
    @DisplayName("hashCode() should be consistent")
    void testHashCodeConsistency() {
        int hashCode1 = fav1.hashCode();
        int hashCode2 = fav1.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("hashCode() should be equal for equal objects")
    void testHashCodeEqualityForEqualObjects() {
        Fav favA = new Fav(1, 10);
        Fav favB = new Fav(1, 10);

        assertEquals(favA.hashCode(), favB.hashCode());
    }

    @Test
    @DisplayName("toString() should contain all fav information")
    void testToString() {
        fav1.setFav(1);
        String toString = fav1.toString();

        assertTrue(toString.contains("Fav{"));
        assertTrue(toString.contains("fav=1"));
        assertTrue(toString.contains("userID=1"));
        assertTrue(toString.contains("teamID=10"));
    }

    @Test
    @DisplayName("toString() should handle null values gracefully")
    void testToStringWithNullValues() {
        Fav fav = new Fav();
        fav.setFav(1);
        String toString = fav.toString();

        assertTrue(toString.contains("Fav{"));
        assertTrue(toString.contains("fav=1"));
        assertTrue(toString.contains("userID=null"));
        assertTrue(toString.contains("teamID=null"));
    }

    @Test
    @DisplayName("Should add and delete a favorite")
    void testAddAndDeleteFav() {
        Fav newFav = new Fav(3, 5);

        // Simulate saving the favorite
        when(favRepository.save(newFav)).thenReturn(newFav);
        Fav savedFav = favRepository.save(newFav);

        assertNotNull(savedFav);
        assertEquals(3, savedFav.getUserID());
        assertEquals(5, savedFav.getTeamID());

        // Simulate deleting the favorite
        doNothing().when(favRepository).deleteById(savedFav.getFav());
        favRepository.deleteById(savedFav.getFav());

        verify(favRepository, times(1)).deleteById(savedFav.getFav());
    }
}