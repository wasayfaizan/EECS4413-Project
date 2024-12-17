package com.example.solemate.controller;

import com.example.solemate.dao.OrderDAO;
import com.example.solemate.dao.OrderDaoImpl;
import com.example.solemate.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderDaoImpl orderDAO;

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        try {
            return orderDAO.getAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get an order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        try {
            return orderDAO.getOrderById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Create a new order
    @PostMapping
    public String createOrder(@RequestBody Order order) {
        try {
            orderDAO.createOrder(order);
            return "Order created successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating order: " + e.getMessage();
        }
    }

    // Update an existing order's status
    @PutMapping("/{id}/status")
    public String updateOrderStatus(@PathVariable int id, @RequestParam String status) {
        try {
            orderDAO.updateOrderStatus(id, status);
            return "Order status updated to: " + status;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating order status: " + e.getMessage();
        }
    }

    // Update the total amount of an order
    @PutMapping("/{id}/total-amount")
    public String updateOrderTotalAmount(@PathVariable int id, @RequestParam double totalAmount) {
        try {
            Order order = orderDAO.getOrderById(id);
            if (order != null) {
                order.setTotalAmount(totalAmount);
                orderDAO.updateOrder(order);
                return "Order total amount updated to: " + totalAmount;
            }
            return "Order not found!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating order total amount: " + e.getMessage();
        }
    }

    // Update the order date
    @PutMapping("/{id}/order-date")
    public String updateOrderDate(@PathVariable int id, @RequestParam String orderDate) {
        try {
            Order order = orderDAO.getOrderById(id);
            if (order != null) {
                order.setOrderDate(orderDate);
                orderDAO.updateOrder(order);
                return "Order date updated to: " + orderDate;
            }
            return "Order not found!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating order date: " + e.getMessage();
        }
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable int id) {
        try {
            orderDAO.deleteOrder(id);
            return "Order deleted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting order: " + e.getMessage();
        }
    }

    // Get all orders for a specific user
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable int userId) {
        try {
            return orderDAO.getOrdersByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
