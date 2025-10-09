package com.example.sportsbetting;

import com.example.sportsbetting.entities.Fav;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Fav Repository
 * 
 * This interface provides methods for interacting with the FAV database table.
 * It extends JpaRepository to inherit standard CRUD operations.
 * Additional methods are defined for fetching favorites by user and team.
 */
public interface FavRepository extends JpaRepository<Fav, Integer> {
    // Find favorites by user ID
    List<Fav> findByUserID(Integer userID);
    
    // Find favorites by team ID
    List<Fav> findByTeamID(Integer teamID);
    
    // Find specific favorite by user and team
    Optional<Fav> findByUserIDAndTeamID(Integer userID, Integer teamID);
    
    // Check if a favorite exists for user and team
    boolean existsByUserIDAndTeamID(Integer userID, Integer teamID);
}