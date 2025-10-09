package com.example.sportsbetting.app.entities;

import com.example.sportsbetting.entities.Fav;
import com.example.sportsbetting.FavRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Favorite Search Tests")
@ExtendWith(MockitoExtension.class)
class FavSearchTest {

    @Mock
    private FavRepository favRepository;

    @Test
    @DisplayName("Search Favorite by User ID")
    void testSearchFavByUserId() {
        Fav fav = new Fav(1, 1, 10, "Golden State Warriors");
        when(favRepository.findByUserID(1)).thenReturn(List.of(fav));

        List<Fav> result = favRepository.findByUserID(1);

        assertFalse(result.isEmpty());
        assertEquals("Golden State Warriors", result.get(0).getTeamName());
    }
}