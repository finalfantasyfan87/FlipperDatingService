package com.dating.controller;

import com.dating.model.User;
import com.dating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
        return !userService.getAllUsers().stream().toList().isEmpty() ? ResponseEntity.ok(userService.getAllUsers()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return userService.getUserById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/name/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
        return userService.findUserByUserName(userName) != null ? ResponseEntity.ok(userService.findUserByUserName(userName)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/zodiac/{zodiacSign}")
    public ResponseEntity<List<User>> getUsersByZodiacSign(@PathVariable String zodiacSign){
return (long) userService.getUsersByZodiacSign(zodiacSign).size() > 0 ? ResponseEntity.ok(userService.getUsersByZodiacSign(zodiacSign)) : ResponseEntity.notFound().build();
}

@PostMapping("/addUser")
public ResponseEntity<?> addUser(@RequestBody User user) {
    User userToSave = userService.saveUser(user);
    return ResponseEntity.created(URI.create("/addUser/" + userToSave.getId()))
            .body(userToSave);
}
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working!");
    }
}
