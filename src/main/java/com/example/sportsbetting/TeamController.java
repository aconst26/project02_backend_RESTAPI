package com.example.sportsbetting;

import com.example.sportsbetting.entities.Team;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = { "exp://192.168.0.31:8081" }) // Adjust for your frontend
public class TeamController {

    private final TeamRepository teamRepository;

    // Constructor injection for TeamRepository
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Get all teams with HATEOAS links
    @GetMapping
    public CollectionModel<EntityModel<Team>> getAllTeams() {
        List<EntityModel<Team>> teams = teamRepository.findAll().stream()
                .map(team -> EntityModel.of(team,
                        linkTo(methodOn(TeamController.class).getTeamById(team.getTeamID())).withSelfRel(),
                        linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams")))
                .collect(Collectors.toList());

        return CollectionModel.of(teams,
                linkTo(methodOn(TeamController.class).getAllTeams()).withSelfRel());
    }

    // Get a team by ID with HATEOAS links
    @GetMapping("/{id}")
    public EntityModel<Team> getTeamById(@PathVariable Integer id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id.longValue()));

        return EntityModel.of(team,
                linkTo(methodOn(TeamController.class).getTeamById(id)).withSelfRel(),
                linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams"));
    }

    // Create a new team
    @PostMapping
    public Team newTeam(@RequestBody Team newTeam) {
        return teamRepository.save(newTeam);
    }

    // Update an existing team or create a new one if not found
    @PutMapping("/{id}")
    public Team replaceTeam(@RequestBody Team newTeam, @PathVariable Integer id) {
        return teamRepository.findById(id).map(team -> {
            team.setName(newTeam.getName());
            team.setDivision(newTeam.getDivision());
            team.setConference(newTeam.getConference());
            return teamRepository.save(team);
        }).orElseGet(() -> teamRepository.save(newTeam));
    }

    // Delete a team by ID
    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Integer id) {
        teamRepository.deleteById(id);
    }
}