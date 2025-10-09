package com.example.sportsbetting.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity class representing a Game in the database.
 */
@Entity
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameID;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "hometeamID")
    private Integer hometeamID;

    @Column(name = "hometeamName")
    private String hometeamName;

    @Column(name = "awayteamID")
    private Integer awayteamID;

    @Column(name = "awayteamName")
    private String awayteamName;
    
    // Many-to-one relationship with Team for home team
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hometeamID", insertable = false, updatable = false)
    @JsonIgnore
    private Team homeTeam;
    
    // Many-to-one relationship with Team for away team
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awayteamID", insertable = false, updatable = false)
    @JsonIgnore
    private Team awayTeam;

    // Constructors
    public Game() {
    }

    public Game(LocalDate date, Integer hometeamID, String hometeamName, Integer awayteamID, String awayteamName) {
        this.date = date;
        this.hometeamID = hometeamID;
        this.hometeamName = hometeamName;
        this.awayteamID = awayteamID;
        this.awayteamName = awayteamName;
    }

    // Constructor with ID for preloading
    public Game(Integer gameID, LocalDate date, Integer hometeamID, String hometeamName, Integer awayteamID, String awayteamName) {
        this.gameID = gameID;
        this.date = date;
        this.hometeamID = hometeamID;
        this.hometeamName = hometeamName;
        this.awayteamID = awayteamID;
        this.awayteamName = awayteamName;
    }

    // Getters and setters
    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getHometeamID() {
        return hometeamID;
    }

    public void setHometeamID(Integer hometeamID) {
        this.hometeamID = hometeamID;
    }

    public String getHometeamName() {
        return hometeamName;
    }

    public void setHometeamName(String hometeamName) {
        this.hometeamName = hometeamName;
    }

    public Integer getAwayteamID() {
        return awayteamID;
    }

    public void setAwayteamID(Integer awayteamID) {
        this.awayteamID = awayteamID;
    }

    public String getAwayteamName() {
        return awayteamName;
    }

    public void setAwayteamName(String awayteamName) {
        this.awayteamName = awayteamName;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameID, game.gameID) &&
                Objects.equals(date, game.date) &&
                Objects.equals(hometeamID, game.hometeamID) &&
                Objects.equals(hometeamName, game.hometeamName) &&
                Objects.equals(awayteamID, game.awayteamID) &&
                Objects.equals(awayteamName, game.awayteamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, date, hometeamID, awayteamID, hometeamName, awayteamName);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", date=" + date +
                ", hometeamID=" + hometeamID +
                ", hometeamName='" + hometeamName + '\'' +
                ", awayteamID=" + awayteamID +
                ", awayteamName='" + awayteamName + '\'' +
                '}';
    }
}