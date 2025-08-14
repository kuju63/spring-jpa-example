package io.github.kuju63.example.demo2;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        return userRepository.findAll(Specification.allOf(UserSpecification.byEnabled(true).and(UserSpecification.byName(username).or(UserSpecification.byEmail(email)))));
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable UUID userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    @PostMapping("/users")
    public User createUser(User user) {
        // Here you would typically validate the user and set defaults
        return userRepository.save(user);
    }
}
