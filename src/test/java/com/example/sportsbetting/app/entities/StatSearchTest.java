package com.example.sportsbetting.app.entities;

import com.example.sportsbetting.entities.Stat;
import com.example.sportsbetting.StatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Stat Search Tests")
@ExtendWith(MockitoExtension.class)
class StatSearchTest {

    @Mock
    private StatRepository statRepository;

    @Test
    @DisplayName("Search Stat by Team ID")
    void testSearchStatByTeamId() {
        Stat stat = new Stat(1, "Cleveland Cavaliers", 1, new BigDecimal("36.5"), new BigDecimal("48.1"), 13, 25, 44, new BigDecimal("0.585"));
        when(statRepository.findByTeamID(1)).thenReturn(Optional.of(stat));

        Optional<Stat> result = statRepository.findByTeamID(1);

        assertTrue(result.isPresent());
        assertEquals("Cleveland Cavaliers", result.get().getTeamName());
    }
}