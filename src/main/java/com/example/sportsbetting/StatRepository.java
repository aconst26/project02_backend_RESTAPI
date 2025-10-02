package com.example.sportsbetting;

import com.example.sportsbetting.entities.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatRepository extends JpaRepository<Stat, Integer> {
    // Fetch stats by team ID
    Optional<Stat> findByTeamID(Integer teamID);
}