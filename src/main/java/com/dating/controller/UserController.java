package com.dating.controller;

import com.dating.model.User;
import com.dating.service.UserService;
import com.dating.utility.ValidationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping( "/userapi")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    /*TODO: global exception handler and validationutil class, also data sanization class, move cross orgin to security class*/

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return users.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return userService.getUserById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/name/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
        User user = userService.findUserByUserName(userName);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/zodiac/{zodiacSign}")
    public ResponseEntity<List<User>> getUsersByZodiacSign(@PathVariable String zodiacSign){
        List<User> users = userService.getUsersByZodiacSign(zodiacSign);
        return users.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (!ValidationUtil.isEmailValid(user.getEmail())) {
            return ResponseEntity.badRequest().build();
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
