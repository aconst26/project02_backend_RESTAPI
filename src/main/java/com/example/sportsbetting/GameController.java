package com.example.sportsbetting;

import com.example.sportsbetting.entities.Game;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for managing Game entities.
 * Provides endpoints for CRUD operations on games.
 */
@RestController
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepository;

    // Constructor injection for GameRepository
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // Get all games with HATEOAS links
    @GetMapping
    public CollectionModel<EntityModel<Game>> getAllGames() {
        List<EntityModel<Game>> games = gameRepository.findAll().stream()
                .map(game -> EntityModel.of(game,
                        linkTo(methodOn(GameController.class).getGameById(game.getGameID())).withSelfRel(),
                        linkTo(methodOn(GameController.class).getAllGames()).withRel("games")))
                .collect(Collectors.toList());

        return CollectionModel.of(games,
                linkTo(methodOn(GameController.class).getAllGames()).withSelfRel());
    }

    // Get a game by ID with HATEOAS links
    @GetMapping("/{id}")
    public EntityModel<Game> getGameById(@PathVariable Integer id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id.longValue()));

        return EntityModel.of(game,
                linkTo(methodOn(GameController.class).getGameById(id)).withSelfRel(),
                linkTo(methodOn(GameController.class).getAllGames()).withRel("games"));
    }

    // Get games by team ID (either home or away)
    @GetMapping("/team/{teamId}")
    public CollectionModel<EntityModel<Game>> getGamesByTeam(@PathVariable Integer teamId) {
        List<EntityModel<Game>> games = gameRepository.findByHometeamIDOrAwayteamID(teamId, teamId).stream()
                .map(game -> EntityModel.of(game,
                        linkTo(methodOn(GameController.class).getGameById(game.getGameID())).withSelfRel(),
                        linkTo(methodOn(GameController.class).getAllGames()).withRel("games")))
                .collect(Collectors.toList());

        return CollectionModel.of(games,
                linkTo(methodOn(GameController.class).getAllGames()).withSelfRel());
    }

    // Get games by date
    @GetMapping("/date/{date}")
    public CollectionModel<EntityModel<Game>> getGamesByDate(@PathVariable String date) {
        LocalDate gameDate = LocalDate.parse(date);
        List<EntityModel<Game>> games = gameRepository.findByDate(gameDate).stream()
                .map(game -> EntityModel.of(game,
                        linkTo(methodOn(GameController.class).getGameById(game.getGameID())).withSelfRel(),
                        linkTo(methodOn(GameController.class).getAllGames()).withRel("games")))
                .collect(Collectors.toList());

        return CollectionModel.of(games,
                linkTo(methodOn(GameController.class).getAllGames()).withSelfRel());
    }

    // Create a new game
    @PostMapping
    public Game newGame(@RequestBody Game newGame) {
        return gameRepository.save(newGame);
    }

    // Update an existing game or create a new one if not found
    @PutMapping("/{id}")
    public Game replaceGame(@RequestBody Game newGame, @PathVariable Integer id) {
        return gameRepository.findById(id).map(game -> {
            game.setDate(newGame.getDate());
            game.setHometeamID(newGame.getHometeamID());
            game.setAwayteamID(newGame.getAwayteamID());
            return gameRepository.save(game);
        }).orElseGet(() -> {
            newGame.setGameID(id);
            return gameRepository.save(newGame);
        });
    }

    // Delete a game by ID
    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Integer id) {
        gameRepository.deleteById(id);
    }
}