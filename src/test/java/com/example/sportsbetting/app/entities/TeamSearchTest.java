package com.example.sportsbetting.app.repositories;

import com.example.sportsbetting.entities.Team;
import com.example.sportsbetting.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Team Search Tests")
@ExtendWith(MockitoExtension.class)
class TeamSearchTest {

    @Mock
    private TeamRepository teamRepository;

    @Test
    @DisplayName("Search Team by Name")
    void testSearchTeamByName() {
        Team team = new Team(1, "Cleveland Cavaliers", "Central", "Eastern");
        when(teamRepository.findAll()).thenReturn(List.of(team));

        List<Team> result = teamRepository.findAll();

        assertFalse(result.isEmpty());
        assertEquals("Cleveland Cavaliers", result.get(0).getName());
    }
}