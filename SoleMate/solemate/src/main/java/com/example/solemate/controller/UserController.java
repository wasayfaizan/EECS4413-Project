package com.example.solemate.controller;

import com.example.solemate.dao.UserDAO;
import com.example.solemate.dao.UserDaoImpl;
import com.example.solemate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDaoImpl userDAO;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        try {
            return userDAO.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        try {
            userDAO.addUser(user);
            return "User registered successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error registering user: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            List<User> users = userDAO.getAllUsers();
            User user = users.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst().orElse(null);
            if (user != null) {
                return "Login successful!";
            }
            return "Invalid username or password!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during login: " + e.getMessage();
        }
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        try {
            User user = userDAO.getUserById(id);
            if (user != null) {
                user.setUsername(updatedUser.getUsername());
                user.setPassword(updatedUser.getPassword());
                user.setEmail(updatedUser.getEmail());
                user.setFullName(updatedUser.getFullName());
                user.setAddress(updatedUser.getAddress());
                user.setPhoneNumber(updatedUser.getPhoneNumber());
                userDAO.updateUser(user);
                return "User updated successfully!";
            }
            return "User not found!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating user: " + e.getMessage();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        try {
            userDAO.deleteUser(id);
            return "User deleted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting user: " + e.getMessage();
        }
    }
}
