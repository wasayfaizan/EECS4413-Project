package com.example.solemate.dao;

import model.Cart;

public interface CartDAO {
    Cart getCartByUserId(int userId) throws Exception;

    void addProductToCart(int cartId, int productId) throws Exception;

    void removeProductFromCart(int cartId, int productId) throws Exception;
}