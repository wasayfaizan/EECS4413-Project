package model;

import java.util.List;

public class Cart {
    private int id;
    private int userId;
    private List<Product> products;

    // Default constructor
    public Cart() {}

    // Parameterized constructor
    public Cart(int id, int userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Cart{id=" + id + ", userId=" + userId + ", products=" + products + "}";
    }
}
