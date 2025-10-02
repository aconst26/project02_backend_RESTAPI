package com.example.sportsbetting;

import com.example.sportsbetting.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Game Repository
 * 
 * This interface provides methods for interacting with the Game database table.
 * It extends JpaRepository to inherit standard CRUD operations.
 * Additional methods are defined for fetching games by team and date.
 */
public interface GameRepository extends JpaRepository<Game, Integer> {
    // Find games by home team
    List<Game> findByHometeamID(Integer hometeamID);
    
    // Find games by away team
    List<Game> findByAwayteamID(Integer awayteamID);
    
    // Find games by date
    List<Game> findByDate(LocalDate date);
    
    // Find games where a team is either home or away
    List<Game> findByHometeamIDOrAwayteamID(Integer hometeamID, Integer awayteamID);
}