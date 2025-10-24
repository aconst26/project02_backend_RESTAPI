package com.example.sportsbetting.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;

/**
 * Entity class representing a User's Favorite Team in the database.
 */
@Entity
@Table(name = "FAV")
public class Fav {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer fav;

    @Column(name = "userID")
    private Integer userID;

    @Column(name = "teamID")
    private Integer teamID;

    @Column(name = "teamName")
    private String teamName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamID", insertable = false, updatable = false)
    @JsonIgnore
    private Team team;

    // Constructors, getters, setters, equals, hashCode, and toString methods

    // Constructors
    public Fav() {
    }

    public Fav(Integer userID, Integer teamID, String teamName) {
        this.userID = userID;
        this.teamID = teamID;
        this.teamName = teamName;
    }

    // Constructor with ID for preloading
    public Fav(Integer fav, Integer userID, Integer teamID, String teamName) {
        this.fav = fav;
        this.userID = userID;
        this.teamID = teamID;
        this.teamName = teamName;
    }

    // Getters and setters
    public Integer getFav() {
        return fav;
    }

    public void setFav(Integer fav) {
        this.fav = fav;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Fav fav1 = (Fav) o;
        return Objects.equals(fav, fav1.fav) &&
                Objects.equals(userID, fav1.userID) &&
                Objects.equals(teamName, fav1.teamName) &&
                Objects.equals(teamID, fav1.teamID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fav, userID, teamID);
    }

    @Override
    public String toString() {
        return "Fav{" +
                "fav=" + fav +
                ", userID=" + userID +
                ", teamID=" + teamID +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}