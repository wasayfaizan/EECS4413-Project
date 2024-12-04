package com.example.solemate.controller;

import com.example.solemate.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private List<User> users = new ArrayList<>(); // Temporary in-memory storage

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        users.add(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        User user = users.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst().orElse(null);
        if (user != null) {
            return "Login successful!";
        }
        return "Invalid username or password!";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        User user = users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        if (user != null) {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());
            user.setFullName(updatedUser.getFullName());
            user.setAddress(updatedUser.getAddress());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            return "User updated successfully!";
        }
        return "User not found!";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        boolean removed = users.removeIf(user -> user.getId() == id);
        return removed ? "User deleted successfully!" : "User not found!";
    }
}
