package io.github.kuju63.example.demo2;

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
        return userRepository.findAll(
                UserSpecification.byEnabled(true)
                        .and(UserSpecification.byEnabledGroup(true))
                        .and(UserSpecification.byEmail(email).or(UserSpecification.byName(username))));
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable UUID userId) throws UserNotFoundException {
        // Need to filter by enabled user and enabled group
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    @PostMapping("/users")
    public User createUser(User user) {
        // Here you would typically validate the user and set defaults
        return userRepository.save(user);
    }
}
