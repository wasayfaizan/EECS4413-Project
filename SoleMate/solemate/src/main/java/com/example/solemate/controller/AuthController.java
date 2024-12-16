// package com.example.solemate.controller;

// import com.example.solemate.model.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private JwtUtil jwtUtil;

//     @PostMapping("/register")
//     public ResponseEntity<String> register(@RequestBody User user) {
//         userService.register(user);
//         return ResponseEntity.ok("User registered successfully.");
//     }

//     @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
//         User user = userService.authenticate(authRequest.getUsername(), authRequest.getPassword());
//         String token = jwtUtil.generateToken(user.getUsername()); // Generate JWT
//         return ResponseEntity.ok(token); // Return JWT to the user
//     }
// }
