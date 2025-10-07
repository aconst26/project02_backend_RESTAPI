package com.example.sportsbetting.app.entities;

import com.example.sportsbetting.entities.Stat;
import com.example.sportsbetting.StatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Stat Entity Tests")
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class StatTest {

    private Stat stat1;
    private Stat stat2;
    private Stat stat3;

    @Mock
    private StatRepository statRepository;

    @BeforeEach
    void setUp() {
        stat1 = new Stat(1, "Cleveland Cavaliers", 1, new BigDecimal("36.5"), new BigDecimal("48.1"), 13, 25, 44, new BigDecimal("0.585"));
        stat2 = new Stat(2, "Denver Nuggets", 2, new BigDecimal("38.0"), new BigDecimal("49.2"), 12, 27, 46, new BigDecimal("0.610"));
        stat3 = new Stat(3, "Houston Rockets", 3, new BigDecimal("34.1"), new BigDecimal("46.7"), 15, 23, 42, new BigDecimal("0.420"));
    }

    @Test
    @DisplayName("Parameterized constructor should set all fields")
    void testParameterizedConstructor() {
        Stat stat = new Stat(1, "Test Team", 1, new BigDecimal("40.5"), new BigDecimal("50.2"), 10, 30, 50, new BigDecimal("0.750"));

        assertNotNull(stat);
        assertEquals(1, stat.getStatId());
        assertEquals("Test Team", stat.getTeamName());
        assertEquals(1, stat.getTeamID());
        assertEquals(new BigDecimal("40.5"), stat.getPgPercent());
        assertEquals(new BigDecimal("50.2"), stat.getFgPercent());
        assertEquals(10, stat.getTurnovers());
        assertEquals(30, stat.getAssists());
        assertEquals(50, stat.getRebounds());
        assertEquals(new BigDecimal("0.750"), stat.getWinPercent());
    }
}