// package com.example.solemate.controller;
//
// import com.example.solemate.model.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
//
// @RestController
// @RequestMapping("/api/profile")
// public class ProfileController {
//
//     @Autowired
//     private UserService userService;
//
//     @GetMapping("/{id}")
//     public User getProfile(@PathVariable String id) {
//         return userService.getUser(id);
//     }
//
//     @PutMapping("/{id}")
//     public User updateProfile(@PathVariable String id, @RequestBody User user) {
//         return userService.updateUser(id, user);
//     }
//
//     @GetMapping("/{id}/cart")
//     public List<CartItem> getCart(@PathVariable String id) {
//         return userService.getCart(id);
//     }
//
//     @PutMapping("/{id}/cart")
//     public void saveCart(@PathVariable String id, @RequestBody List<CartItem> cart) {
//         userService.saveCart(id, cart);
//     }
//
//     @GetMapping("/{id}/wishlist")
//     public List<String> getWishlist(@PathVariable String id) {
//         return userService.getWishlist(id);
//     }
//
//     @PutMapping("/{id}/wishlist")
//     public void saveWishlist(@PathVariable String id, @RequestBody List<String> wishlist) {
//         userService.saveWishlist(id, wishlist);
//     }
// }
