package com.example.sportsbetting;

import com.example.sportsbetting.entities.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for managing User entities.
 * Provides endpoints for CRUD operations and user login.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = { "exp://192.168.0.31:8081" }) // Adjust for your frontend
public class UserController {
    private final UserRepository repository;

    // Constructor injection for UserRepository
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // Fetch all users with HATEOAS links
    @GetMapping
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = repository.findAll().stream()
                .map(user -> EntityModel.of(user,
                        linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).all()).withRel("users")))
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    // Fetch a single user by ID with HATEOAS links
    @GetMapping("/{id}")
    public EntityModel<User> one(@PathVariable Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.longValue()));

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).one(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }

    // Create a new user
    @PostMapping
    public User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Update an existing user or create a new one if not found
    @PutMapping("/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Integer id) {
        return repository.findById(id).map(user -> {
            user.setUserName(newUser.getUserName());
            user.setEmail(newUser.getEmail());
            user.setUserPassword(newUser.getUserPassword());
            return repository.save(user);
        }).orElseGet(() -> repository.save(newUser));
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    // Authenticate a user by username and password
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        Optional<User> userOpt = repository.getUserByUserName(loginRequest.getUserName());

        if (userOpt.isPresent() && userOpt.get().getUserPassword().equals(loginRequest.getUserPassword())) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }
    }
}