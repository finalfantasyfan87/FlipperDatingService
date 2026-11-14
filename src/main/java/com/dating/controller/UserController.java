package com.dating.controller;

import com.dating.model.User;
import com.dating.service.UserService;
import com.dating.utility.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/userapi")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users were found");
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found"));
    }

    @GetMapping("/user/name/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        User user = userService.findUserByUserName(userName);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/zodiac/{zodiacSign}")
    public ResponseEntity<List<User>> getUsersByZodiacSign(@PathVariable String zodiacSign) {
        List<User> users = userService.getUsersByZodiacSign(zodiacSign);
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users were found for that zodiac sign");
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (!ValidationUtil.isEmailValid(user.getEmail())) {
            throw new IllegalArgumentException("A valid email address is required");
        }
        User userToSave = userService.saveUser(user);
        return ResponseEntity.created(URI.create("/addUser/" + userToSave.getId()))
                .body(userToSave);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working!");
    }
}
