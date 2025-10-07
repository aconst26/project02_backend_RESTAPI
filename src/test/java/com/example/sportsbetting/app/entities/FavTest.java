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
        fav1 = new Fav(1, 10, "Golden State Warriors"); // User 1 likes Warriors (team 10)
        fav2 = new Fav(2, 1, "Cleveland Cavaliers");   // User 2 likes Cavaliers (team 1)
        fav3 = new Fav(1, 2, "Denver Nuggets");        // User 1 likes Nuggets (team 2)
    }

    @Test
    @DisplayName("Parameterized constructor should set userID, teamID, and teamName")
    void testParameterizedConstructor() {
        Fav fav = new Fav(5, 8, "Test Team");

        assertNotNull(fav);
        assertEquals(5, fav.getUserID());
        assertEquals(8, fav.getTeamID());
        assertEquals("Test Team", fav.getTeamName());
        assertNull(fav.getFav());
    }
}