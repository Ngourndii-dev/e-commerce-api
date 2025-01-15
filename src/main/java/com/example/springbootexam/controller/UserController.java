package com.example.springbootexam.controller;

import com.example.springbootexam.model.Promo;
import com.example.springbootexam.model.User;
import com.example.springbootexam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.insert(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping("/{work}")
    public ResponseEntity<User> updateUserWork(@PathVariable String work, @PathVariable int id) {
        return ResponseEntity.ok(userService.updateOccupation(work, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getPromoById(@PathVariable int id) {
        return Optional.ofNullable(userService.getById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<User>> getPromoByOccupation(@PathVariable String occupation) {
        return Optional.ofNullable(userService.searchByOccupation(occupation))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }
}
