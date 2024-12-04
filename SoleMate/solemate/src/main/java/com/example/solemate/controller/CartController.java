package com.example.solemate.controller;

import com.example.solemate.model.Cart;
import com.example.solemate.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private List<Cart> carts = new ArrayList<>(); // Temporary in-memory storage

    @GetMapping("/{userId}")
    public Cart getCartByUserId(@PathVariable int userId) {
        return carts.stream()
                .filter(cart -> cart.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public String createCart(@RequestBody Cart cart) {
        carts.add(cart);
        return "Cart created successfully!";
    }

    @PostMapping("/{cartId}/add-product")
    public String addProductToCart(@PathVariable int cartId, @RequestBody Product product) {
        Cart cart = carts.stream()
                .filter(c -> c.getId() == cartId)
                .findFirst()
                .orElse(null);
        if (cart != null) {
            cart.addProduct(product);
            return "Product added to cart!";
        }
        return "Cart not found!";
    }

    @PostMapping("/{cartId}/remove-product")
    public String removeProductFromCart(@PathVariable int cartId, @RequestBody Product product) {
        Cart cart = carts.stream()
                .filter(c -> c.getId() == cartId)
                .findFirst()
                .orElse(null);
        if (cart != null) {
            cart.removeProduct(product);
            return "Product removed from cart!";
        }
        return "Cart not found!";
    }

    @GetMapping("/{cartId}/total")
    public double getTotalPrice(@PathVariable int cartId) {
        Cart cart = carts.stream()
                .filter(c -> c.getId() == cartId)
                .findFirst()
                .orElse(null);
        return cart != null ? cart.getTotalPrice() : 0;
    }

    @DeleteMapping("/{cartId}")
    public String deleteCart(@PathVariable int cartId) {
        carts.removeIf(cart -> cart.getId() == cartId);
        return "Cart deleted successfully!";
    }
}
