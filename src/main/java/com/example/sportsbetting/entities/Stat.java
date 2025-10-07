package com.example.sportsbetting.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entity class representing team statistics in the database.
 */
@Entity
@Table(name = "Stat")
public class Stat {
    @Id
    private Integer statId;

    @Column(name = "teamID")
    private Integer teamID;

    @Column(name = "teamName")
    private String teamName;
    @Column(name = "PGpercent", precision = 4, scale = 1)
    private BigDecimal pgPercent;

    @Column(name = "FGpercent", precision = 3, scale = 1)
    private BigDecimal fgPercent;

    @Column(name = "Turnovers")
    private Integer turnovers;

    @Column(name = "Assists")
    private Integer assists;

    @Column(name = "Rebound")
    private Integer rebounds;

    @Column(name = "win_percent", precision = 4, scale = 3)
    private BigDecimal winPercent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamID", insertable = false, updatable = false)
    @JsonIgnore
    private Team team;

    // Constructors
    public Stat() {
    }

    public Stat(Integer teamID, String teamName, BigDecimal pgPercent, BigDecimal fgPercent,
            Integer turnovers, Integer assists, Integer rebounds, BigDecimal winPercent) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.pgPercent = pgPercent;
        this.fgPercent = fgPercent;
        this.turnovers = turnovers;
        this.assists = assists;
        this.rebounds = rebounds;
        this.winPercent = winPercent;
    }

    // Constructor with ID for preloading
    public Stat(Integer statId, String teamName, Integer teamID, BigDecimal pgPercent, BigDecimal fgPercent,
            Integer turnovers, Integer assists, Integer rebounds, BigDecimal winPercent) {
        this.statId = statId;
        this.teamName = teamName;
        this.teamID = teamID;
        this.pgPercent = pgPercent;
        this.fgPercent = fgPercent;
        this.turnovers = turnovers;
        this.assists = assists;
        this.rebounds = rebounds;
        this.winPercent = winPercent;
    }

    // Getters and setters
    public Integer getStatId() {
        return statId;
    }

    public void setStatId(Integer statId) {
        this.statId = statId;
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

    public BigDecimal getPgPercent() {
        return pgPercent;
    }

    public void setPgPercent(BigDecimal pgPercent) {
        this.pgPercent = pgPercent;
    }

    public BigDecimal getFgPercent() {
        return fgPercent;
    }

    public void setFgPercent(BigDecimal fgPercent) {
        this.fgPercent = fgPercent;
    }

    public Integer getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(Integer turnovers) {
        this.turnovers = turnovers;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getRebounds() {
        return rebounds;
    }

    public void setRebounds(Integer rebounds) {
        this.rebounds = rebounds;
    }

    public BigDecimal getWinPercent() {
        return winPercent;
    }

    public void setWinPercent(BigDecimal winPercent) {
        this.winPercent = winPercent;
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
        Stat stat = (Stat) o;
        return Objects.equals(statId, stat.statId) &&
                Objects.equals(teamID, stat.teamID) &&
                Objects.equals(teamName, stat.teamName) &&
                Objects.equals(pgPercent, stat.pgPercent) &&
                Objects.equals(fgPercent, stat.fgPercent) &&
                Objects.equals(turnovers, stat.turnovers) &&
                Objects.equals(assists, stat.assists) &&
                Objects.equals(rebounds, stat.rebounds) &&
                Objects.equals(winPercent, stat.winPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statId, teamName, teamID, pgPercent, fgPercent, turnovers, assists, rebounds, winPercent);
    }

    @Override
    public String toString() {
        return "Stat{" +
                "statId=" + statId +
                ", teamID=" + teamID +
                ", teamName='" + teamName + '\'' +
                ", pgPercent=" + pgPercent +
                ", fgPercent=" + fgPercent +
                ", turnovers=" + turnovers +
                ", assists=" + assists +
                ", rebounds=" + rebounds +
                ", winPercent=" + winPercent +
                '}';
    }
}