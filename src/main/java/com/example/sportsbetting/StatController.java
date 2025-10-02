package com.example.sportsbetting;

import com.example.sportsbetting.entities.Stat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for managing Stat entities.
 * Provides endpoints for CRUD operations on team statistics.
 */
@RestController
@RequestMapping("/stats")
@CrossOrigin(origins = { "exp://192.168.0.31:8081" }) // Adjust for your frontend
public class StatController {

    private final StatRepository statRepository;

    // Constructor injection for StatRepository
    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    // Get all stats with HATEOAS links
    @GetMapping
    public CollectionModel<EntityModel<Stat>> getAllStats() {
        List<EntityModel<Stat>> stats = statRepository.findAll().stream()
                .map(stat -> EntityModel.of(stat,
                        linkTo(methodOn(StatController.class).getStatById(stat.getStatId())).withSelfRel(),
                        linkTo(methodOn(StatController.class).getAllStats()).withRel("stats")))
                .collect(Collectors.toList());

        return CollectionModel.of(stats,
                linkTo(methodOn(StatController.class).getAllStats()).withSelfRel());
    }

    // Get a stat by ID with HATEOAS links
    @GetMapping("/{id}")
    public EntityModel<Stat> getStatById(@PathVariable Integer id) {
        Stat stat = statRepository.findById(id)
                .orElseThrow(() -> new StatNotFoundException(id.longValue()));

        return EntityModel.of(stat,
                linkTo(methodOn(StatController.class).getStatById(id)).withSelfRel(),
                linkTo(methodOn(StatController.class).getAllStats()).withRel("stats"));
    }

    // Get stats by team ID
    @GetMapping("/team/{teamId}")
    public EntityModel<Stat> getStatByTeamId(@PathVariable Integer teamId) {
        Stat stat = statRepository.findByTeamID(teamId)
                .orElseThrow(() -> new StatNotFoundException(teamId.longValue()));

        return EntityModel.of(stat,
                linkTo(methodOn(StatController.class).getStatByTeamId(teamId)).withSelfRel(),
                linkTo(methodOn(StatController.class).getAllStats()).withRel("stats"));
    }

    // Create a new stat
    @PostMapping
    public Stat newStat(@RequestBody Stat newStat) {
        return statRepository.save(newStat);
    }

    // Update an existing stat or create a new one if not found
    @PutMapping("/{id}")
    public Stat replaceStat(@RequestBody Stat newStat, @PathVariable Integer id) {
        return statRepository.findById(id).map(stat -> {
            stat.setTeamID(newStat.getTeamID());
            stat.setPgPercent(newStat.getPgPercent());
            stat.setFgPercent(newStat.getFgPercent());
            stat.setTurnovers(newStat.getTurnovers());
            stat.setAssists(newStat.getAssists());
            stat.setRebounds(newStat.getRebounds());
            stat.setWinPercent(newStat.getWinPercent());
            return statRepository.save(stat);
        }).orElseGet(() -> {
            newStat.setStatId(id);
            return statRepository.save(newStat);
        });
    }

    // Delete a stat by ID
    @DeleteMapping("/{id}")
    public void deleteStat(@PathVariable Integer id) {
        statRepository.deleteById(id);
    }
}