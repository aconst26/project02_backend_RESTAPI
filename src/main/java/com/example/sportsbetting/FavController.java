package com.example.sportsbetting;

import com.example.sportsbetting.entities.Fav;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for managing Fav entities.
 * Provides endpoints for CRUD operations on user favorites.
 */
@RestController
@RequestMapping("/favs")
@CrossOrigin(origins = { "exp://192.168.0.31:8081" }) // Adjust for your frontend
public class FavController {

    private final FavRepository favRepository;

    // Constructor injection for FavRepository
    public FavController(FavRepository favRepository) {
        this.favRepository = favRepository;
    }

    // Get all favorites with HATEOAS links
    @GetMapping
    public CollectionModel<EntityModel<Fav>> getAllFavs() {
        List<EntityModel<Fav>> favs = favRepository.findAll().stream()
                .map(fav -> EntityModel.of(fav,
                        linkTo(methodOn(FavController.class).getFavById(fav.getFav())).withSelfRel(),
                        linkTo(methodOn(FavController.class).getAllFavs()).withRel("favs")))
                .collect(Collectors.toList());

        return CollectionModel.of(favs,
                linkTo(methodOn(FavController.class).getAllFavs()).withSelfRel());
    }

    // Get a favorite by ID with HATEOAS links
    @GetMapping("/{id}")
    public EntityModel<Fav> getFavById(@PathVariable Integer id) {
        Fav fav = favRepository.findById(id)
                .orElseThrow(() -> new FavNotFoundException(id.longValue()));

        return EntityModel.of(fav,
                linkTo(methodOn(FavController.class).getFavById(id)).withSelfRel(),
                linkTo(methodOn(FavController.class).getAllFavs()).withRel("favs"));
    }

    // Get favorites by user ID
    @GetMapping("/user/{userId}")
    public CollectionModel<EntityModel<Fav>> getFavsByUser(@PathVariable Integer userId) {
        List<EntityModel<Fav>> favs = favRepository.findByUserID(userId).stream()
                .map(fav -> EntityModel.of(fav,
                        linkTo(methodOn(FavController.class).getFavById(fav.getFav())).withSelfRel(),
                        linkTo(methodOn(FavController.class).getAllFavs()).withRel("favs")))
                .collect(Collectors.toList());

        return CollectionModel.of(favs,
                linkTo(methodOn(FavController.class).getAllFavs()).withSelfRel());
    }

    // Get favorites by team ID
    @GetMapping("/team/{teamId}")
    public CollectionModel<EntityModel<Fav>> getFavsByTeam(@PathVariable Integer teamId) {
        List<EntityModel<Fav>> favs = favRepository.findByTeamID(teamId).stream()
                .map(fav -> EntityModel.of(fav,
                        linkTo(methodOn(FavController.class).getFavById(fav.getFav())).withSelfRel(),
                        linkTo(methodOn(FavController.class).getAllFavs()).withRel("favs")))
                .collect(Collectors.toList());

        return CollectionModel.of(favs,
                linkTo(methodOn(FavController.class).getAllFavs()).withSelfRel());
    }

    // Create a new favorite
    @PostMapping
    public Fav newFav(@RequestBody Fav newFav) {
        return favRepository.save(newFav);
    }

    // Update an existing favorite or create a new one if not found
    @PutMapping("/{id}")
    public Fav replaceFav(@RequestBody Fav newFav, @PathVariable Integer id) {
        return favRepository.findById(id).map(fav -> {
            fav.setUserID(newFav.getUserID());
            fav.setTeamID(newFav.getTeamID());
            return favRepository.save(fav);
        }).orElseGet(() -> {
            newFav.setFav(id);
            return favRepository.save(newFav);
        });
    }

    // Delete a favorite
    @DeleteMapping("/{id}")
    void deleteFav(@PathVariable Integer id) {
        favRepository.deleteById(id);
    }
}