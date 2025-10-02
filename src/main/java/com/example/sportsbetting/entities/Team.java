package com.example.sportsbetting.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Team")
public class Team {
    @Id
    private Integer teamID; // Removed auto-generation to match your database
    
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Column(name = "Division", length = 100) // Capitalized to match DB
    private String Division;
    
    @Column(name = "conference", length = 25)
    private String conference;

    // Constructors
    public Team() {
    }

    public Team(String name, String Division, String conference) {
        this.name = name;
        this.Division = Division;
        this.conference = conference;
    }

    // Constructor with ID for preloading
    public Team(Integer teamID, String name, String Division, String conference) {
        this.teamID = teamID;
        this.name = name;
        this.Division = Division;
        this.conference = conference;
    }

    // Getters and setters
    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String Division) {
        this.Division = Division;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    // Override equals() to compare teams based on their fields
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) &&
                Objects.equals(Division, team.Division) &&
                Objects.equals(conference, team.conference);
    }

    // Override hashCode() for consistent hashing
    @Override
    public int hashCode() {
        return Objects.hash(name, Division, conference);
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamID=" + teamID +
                ", name='" + name + '\'' +
                ", Division='" + Division + '\'' +
                ", conference='" + conference + '\'' +
                '}';
    }
}