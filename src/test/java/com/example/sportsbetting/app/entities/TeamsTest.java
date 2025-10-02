package com.example.sportsbetting.app.entities;

import com.example.sportsbetting.entities.Team;
import com.example.sportsbetting.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
// Replace deprecated MockBean
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Team Entity Tests")
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TeamTest {

    private Team team1;
    private Team team2;
    private Team team3;

    // Replace @MockBean with @Mock
    @Mock
    private TeamRepository teamRepository;


    @BeforeEach
    void setUp() {
        team1 = new Team("Cleveland Cavaliers", "Central", "Eastern");
        team2 = new Team("Denver Nuggets", "Northwest", "Western");
        team3 = new Team("Houston Rockets", "Southwest", "Western");
    }

    @Test
    @DisplayName("Default constructor should create empty team")
    void testDefaultConstructor() {
        Team emptyTeam = new Team();

        assertNotNull(emptyTeam);
        assertNull(emptyTeam.getTeamID());
        assertNull(emptyTeam.getName());
        assertNull(emptyTeam.getDivision());
        assertNull(emptyTeam.getConference());
    }

    @Test
    @DisplayName("Parameterized constructor should set name, division, and conference")
    void testParameterizedConstructor() {
        Team team = new Team("Test Team", "Test Division", "Test Conference");

        assertNotNull(team);
        assertEquals("Test Team", team.getName());
        assertEquals("Test Division", team.getDivision());
        assertEquals("Test Conference", team.getConference());
        assertNull(team.getTeamID());
    }

    @Test
    @DisplayName("Constructor with ID should set all fields")
    void testParameterizedConstructorWithId() {
        Team team = new Team(1, "Test Team", "Test Division", "Test Conference");

        assertNotNull(team);
        assertEquals(1, team.getTeamID());
        assertEquals("Test Team", team.getName());
        assertEquals("Test Division", team.getDivision());
        assertEquals("Test Conference", team.getConference());
    }

    @Test
    @DisplayName("Setters and getters should work correctly")
    void testSettersAndGetters() {
        Team team = new Team();

        team.setTeamID(1);
        team.setName("New Team");
        team.setDivision("New Division");
        team.setConference("New Conference");

        assertEquals(1, team.getTeamID());
        assertEquals("New Team", team.getName());
        assertEquals("New Division", team.getDivision());
        assertEquals("New Conference", team.getConference());
    }

    @Test
    @DisplayName("equals() should return true for teams with same content")
    void testEqualsWithSameContent() {
        Team teamA = new Team("Cleveland Cavaliers", "Central", "Eastern");
        Team teamB = new Team("Cleveland Cavaliers", "Central", "Eastern");

        assertTrue(teamA.equals(teamB));
        assertTrue(teamB.equals(teamA));
    }

    @Test
    @DisplayName("equals() should return false for teams with different content")
    void testEqualsWithDifferentContent() {
        assertFalse(team1.equals(team2));
        assertFalse(team2.equals(team1));
    }

    @Test
    @DisplayName("equals() should return false when compared to null")
    void testEqualsWithNull() {
        assertFalse(team1.equals(null));
    }

    @Test
    @DisplayName("equals() should return false when compared to different type")
    void testEqualsWithDifferentType() {
        assertFalse(team1.equals("Not a team"));
    }

    @Test
    @DisplayName("equals() should return true when object is compared to itself")
    void testEqualsWithSameObject() {
        assertTrue(team1.equals(team1));
    }

    @Test
    @DisplayName("hashCode() should be consistent")
    void testHashCodeConsistency() {
        int hashCode1 = team1.hashCode();
        int hashCode2 = team1.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("hashCode() should be equal for equal objects")
    void testHashCodeEqualityForEqualObjects() {
        Team teamA = new Team("Cleveland Cavaliers", "Central", "Eastern");
        Team teamB = new Team("Cleveland Cavaliers", "Central", "Eastern");

        assertEquals(teamA.hashCode(), teamB.hashCode());
    }

    @Test
    @DisplayName("toString() should contain all team information")
    void testToString() {
        team1.setTeamID(1);
        String toString = team1.toString();

        assertTrue(toString.contains("Team{"));
        assertTrue(toString.contains("teamID=1"));
        assertTrue(toString.contains("name='Cleveland Cavaliers'"));
        assertTrue(toString.contains("Division='Central'"));
        assertTrue(toString.contains("conference='Eastern'"));
    }

    @Test
    @DisplayName("toString() should handle null values gracefully")
    void testToStringWithNullValues() {
        Team team = new Team();
        team.setTeamID(1);
        String toString = team.toString();

        assertTrue(toString.contains("Team{"));
        assertTrue(toString.contains("teamID=1"));
        assertTrue(toString.contains("name='null'"));
        assertTrue(toString.contains("Division='null'"));
        assertTrue(toString.contains("conference='null'"));
    }

    @Test
    @DisplayName("Should add and delete a team")
    void testAddAndDeleteTeam() {
        Team newTeam = new Team("New Team", "New Division", "New Conference");

        // Simulate saving the team
        when(teamRepository.save(newTeam)).thenReturn(newTeam);
        Team savedTeam = teamRepository.save(newTeam);

        assertNotNull(savedTeam);
        assertEquals("New Team", savedTeam.getName());
        assertEquals("New Division", savedTeam.getDivision());
        assertEquals("New Conference", savedTeam.getConference());

        // Simulate deleting the team
        doNothing().when(teamRepository).deleteById(savedTeam.getTeamID());
        teamRepository.deleteById(savedTeam.getTeamID());

        verify(teamRepository, times(1)).deleteById(savedTeam.getTeamID());
    }
}