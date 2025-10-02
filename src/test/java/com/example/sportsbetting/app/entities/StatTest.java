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
        stat1 = new Stat(1, new BigDecimal("36.5"), new BigDecimal("48.1"), 13, 25, 44, new BigDecimal("0.585"));
        stat2 = new Stat(2, new BigDecimal("38.0"), new BigDecimal("49.2"), 12, 27, 46, new BigDecimal("0.610"));
        stat3 = new Stat(3, new BigDecimal("34.1"), new BigDecimal("46.7"), 15, 23, 42, new BigDecimal("0.420"));
    }

    @Test
    @DisplayName("Default constructor should create empty stat")
    void testDefaultConstructor() {
        Stat emptyStat = new Stat();

        assertNotNull(emptyStat);
        assertNull(emptyStat.getStatId());
        assertNull(emptyStat.getTeamID());
        assertNull(emptyStat.getPgPercent());
        assertNull(emptyStat.getFgPercent());
        assertNull(emptyStat.getTurnovers());
        assertNull(emptyStat.getAssists());
        assertNull(emptyStat.getRebounds());
        assertNull(emptyStat.getWinPercent());
    }

    @Test
    @DisplayName("Parameterized constructor should set all fields except statId")
    void testParameterizedConstructor() {
        Stat stat = new Stat(1, new BigDecimal("40.5"), new BigDecimal("50.2"), 10, 30, 50, new BigDecimal("0.750"));

        assertNotNull(stat);
        assertEquals(1, stat.getTeamID());
        assertEquals(new BigDecimal("40.5"), stat.getPgPercent());
        assertEquals(new BigDecimal("50.2"), stat.getFgPercent());
        assertEquals(10, stat.getTurnovers());
        assertEquals(30, stat.getAssists());
        assertEquals(50, stat.getRebounds());
        assertEquals(new BigDecimal("0.750"), stat.getWinPercent());
        assertNull(stat.getStatId());
    }

    @Test
    @DisplayName("Constructor with ID should set all fields")
    void testParameterizedConstructorWithId() {
        Stat stat = new Stat(1, 1, new BigDecimal("40.5"), new BigDecimal("50.2"), 10, 30, 50, new BigDecimal("0.750"));

        assertNotNull(stat);
        assertEquals(1, stat.getStatId());
        assertEquals(1, stat.getTeamID());
        assertEquals(new BigDecimal("40.5"), stat.getPgPercent());
        assertEquals(new BigDecimal("50.2"), stat.getFgPercent());
        assertEquals(10, stat.getTurnovers());
        assertEquals(30, stat.getAssists());
        assertEquals(50, stat.getRebounds());
        assertEquals(new BigDecimal("0.750"), stat.getWinPercent());
    }

    @Test
    @DisplayName("Setters and getters should work correctly")
    void testSettersAndGetters() {
        Stat stat = new Stat();

        stat.setStatId(1);
        stat.setTeamID(5);
        stat.setPgPercent(new BigDecimal("35.0"));
        stat.setFgPercent(new BigDecimal("45.0"));
        stat.setTurnovers(20);
        stat.setAssists(25);
        stat.setRebounds(40);
        stat.setWinPercent(new BigDecimal("0.500"));

        assertEquals(1, stat.getStatId());
        assertEquals(5, stat.getTeamID());
        assertEquals(new BigDecimal("35.0"), stat.getPgPercent());
        assertEquals(new BigDecimal("45.0"), stat.getFgPercent());
        assertEquals(20, stat.getTurnovers());
        assertEquals(25, stat.getAssists());
        assertEquals(40, stat.getRebounds());
        assertEquals(new BigDecimal("0.500"), stat.getWinPercent());
    }

    @Test
    @DisplayName("equals() should return true for stats with same content")
    void testEqualsWithSameContent() {
        Stat statA = new Stat(1, 1, new BigDecimal("36.5"), new BigDecimal("48.1"), 13, 25, 44, new BigDecimal("0.585"));
        Stat statB = new Stat(1, 1, new BigDecimal("36.5"), new BigDecimal("48.1"), 13, 25, 44, new BigDecimal("0.585"));

        assertTrue(statA.equals(statB));
        assertTrue(statB.equals(statA));
    }

    @Test
    @DisplayName("equals() should return false for stats with different content")
    void testEqualsWithDifferentContent() {
        assertFalse(stat1.equals(stat2));
        assertFalse(stat2.equals(stat1));
    }

    @Test
    @DisplayName("equals() should return false when compared to null")
    void testEqualsWithNull() {
        assertFalse(stat1.equals(null));
    }

    @Test
    @DisplayName("equals() should return false when compared to different type")
    void testEqualsWithDifferentType() {
        assertFalse(stat1.equals("Not a stat"));
    }

    @Test
    @DisplayName("equals() should return true when object is compared to itself")
    void testEqualsWithSameObject() {
        assertTrue(stat1.equals(stat1));
    }

    @Test
    @DisplayName("hashCode() should be consistent")
    void testHashCodeConsistency() {
        int hashCode1 = stat1.hashCode();
        int hashCode2 = stat1.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("hashCode() should be equal for equal objects")
    void testHashCodeEqualityForEqualObjects() {
        Stat statA = new Stat(1, 1, new BigDecimal("36.5"), new BigDecimal("48.1"), 13, 25, 44, new BigDecimal("0.585"));
        Stat statB = new Stat(1, 1, new BigDecimal("36.5"), new BigDecimal("48.1"), 13, 25, 44, new BigDecimal("0.585"));

        assertEquals(statA.hashCode(), statB.hashCode());
    }

    @Test
    @DisplayName("toString() should contain all stat information")
    void testToString() {
        stat1.setStatId(1);
        String toString = stat1.toString();

        assertTrue(toString.contains("Stat{"));
        assertTrue(toString.contains("statId=1"));
        assertTrue(toString.contains("teamID=1"));
        assertTrue(toString.contains("pgPercent=36.5"));
        assertTrue(toString.contains("fgPercent=48.1"));
        assertTrue(toString.contains("turnovers=13"));
        assertTrue(toString.contains("assists=25"));
        assertTrue(toString.contains("rebounds=44"));
        assertTrue(toString.contains("winPercent=0.585"));
    }

    @Test
    @DisplayName("toString() should handle null values gracefully")
    void testToStringWithNullValues() {
        Stat stat = new Stat();
        stat.setStatId(1);
        String toString = stat.toString();

        assertTrue(toString.contains("Stat{"));
        assertTrue(toString.contains("statId=1"));
        assertTrue(toString.contains("teamID=null"));
        assertTrue(toString.contains("pgPercent=null"));
        assertTrue(toString.contains("fgPercent=null"));
        assertTrue(toString.contains("turnovers=null"));
        assertTrue(toString.contains("assists=null"));
        assertTrue(toString.contains("rebounds=null"));
        assertTrue(toString.contains("winPercent=null"));
    }

    @Test
    @DisplayName("Should add and delete a stat")
    void testAddAndDeleteStat() {
        Stat newStat = new Stat(5, new BigDecimal("42.0"), new BigDecimal("52.0"), 8, 35, 55, new BigDecimal("0.800"));

        // Simulate saving the stat
        when(statRepository.save(newStat)).thenReturn(newStat);
        Stat savedStat = statRepository.save(newStat);

        assertNotNull(savedStat);
        assertEquals(5, savedStat.getTeamID());
        assertEquals(new BigDecimal("42.0"), savedStat.getPgPercent());
        assertEquals(new BigDecimal("52.0"), savedStat.getFgPercent());
        assertEquals(8, savedStat.getTurnovers());
        assertEquals(35, savedStat.getAssists());
        assertEquals(55, savedStat.getRebounds());
        assertEquals(new BigDecimal("0.800"), savedStat.getWinPercent());

        // Simulate deleting the stat
        doNothing().when(statRepository).deleteById(savedStat.getStatId());
        statRepository.deleteById(savedStat.getStatId());

        verify(statRepository, times(1)).deleteById(savedStat.getStatId());
    }
}