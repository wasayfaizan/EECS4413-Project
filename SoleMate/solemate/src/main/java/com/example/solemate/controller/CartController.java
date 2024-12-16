package com.example.solemate.controller;

import com.example.solemate.dao.CartDAO;
import com.example.solemate.model.Cart;
import com.example.solemate.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartDAO cartDAO;

    @GetMapping("/{userId}")
    public Cart getCartByUserId(@PathVariable int userId) {
        try {
            return cartDAO.getCartByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping
    public String createCart(@RequestBody Cart cart) {
        try {
            cartDAO.createCart(cart);
            return "Cart created successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating cart: " + e.getMessage();
        }
    }

    @PostMapping("/{cartId}/add-product")
    public String addProductToCart(@PathVariable int cartId, @RequestBody Product product) {
        try {
            cartDAO.addProductToCart(cartId, product.getId());
            return "Product added to cart!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error adding product to cart: " + e.getMessage();
        }
    }

    @PostMapping("/{cartId}/remove-product")
    public String removeProductFromCart(@PathVariable int cartId, @RequestBody Product product) {
        try {
            cartDAO.removeProductFromCart(cartId, product.getId());
            return "Product removed from cart!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error removing product from cart: " + e.getMessage();
        }
    }

    @GetMapping("/{cartId}/total")
    public double getTotalPrice(@PathVariable int cartId) {
        try {
            Cart cart = cartDAO.getCartByUserId(cartId);
            return cart != null ? cart.getTotalPrice() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @DeleteMapping("/{cartId}")
    public String deleteCart(@PathVariable int cartId) {
        try {
            cartDAO.deleteCart(cartId);
            return "Cart deleted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting cart: " + e.getMessage();
        }
    }
}
