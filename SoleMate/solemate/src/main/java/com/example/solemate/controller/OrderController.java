package com.example.solemate.controller;

import com.example.solemate.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private List<Order> orders = new ArrayList<>(); // Temporary in-memory storage

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orders;
    }

    // Get an order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Create a new order
    @PostMapping
    public String createOrder(@RequestBody Order order) {
        orders.add(order);
        return "Order created successfully!";
    }

    // Update an existing order's status
    @PutMapping("/{id}/status")
    public String updateOrderStatus(@PathVariable int id, @RequestParam String status) {
        Order order = orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);

        if (order != null) {
            order.setStatus(status);
            return "Order status updated to: " + status;
        }
        return "Order not found!";
    }

    // Update the total amount of an order
    @PutMapping("/{id}/total-amount")
    public String updateOrderTotalAmount(@PathVariable int id, @RequestParam double totalAmount) {
        Order order = orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);

        if (order != null) {
            order.setTotalAmount(totalAmount);
            return "Order total amount updated to: " + totalAmount;
        }
        return "Order not found!";
    }

    // Update the order date
    @PutMapping("/{id}/order-date")
    public String updateOrderDate(@PathVariable int id, @RequestParam String orderDate) {
        Order order = orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);

        if (order != null) {
            order.setOrderDate(orderDate);
            return "Order date updated to: " + orderDate;
        }
        return "Order not found!";
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable int id) {
        boolean removed = orders.removeIf(order -> order.getId() == id);
        return removed ? "Order deleted successfully!" : "Order not found!";
    }

    // Get all orders for a specific user
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable int userId) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserId() == userId) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
}
