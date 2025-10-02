package com.example.sportsbetting.app.entities;

import com.example.sportsbetting.homeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Home Controller Tests")
@SpringBootTest
@ActiveProfiles("test")
public class homeTest {
    
    @Test
    @DisplayName("Home route should return NBA season message")
    void testHomeRoute() {
        homeController controller = new homeController();
        String result = controller.home();
        
        assertEquals("25-26 NBA season", result);
        assertNotNull(result);
        assertTrue(result.contains("NBA"));
        assertTrue(result.contains("25-26"));
    }
}