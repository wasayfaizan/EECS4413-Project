package model;

import java.util.List;

public class Order {
    private int id;
    private int userId;
    private String status; // E.g., Pending, Shipped, Delivered
    private double totalAmount;
    private String orderDate;
    private String orderStatus; // E.g., Pending, Shipped, Delivered

    // Default constructor
    public Order() {}

    // Parameterized constructor
    public Order(int id, int userId, String status, double totalAmount, String orderDate, String orderStatus) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", userId=" + userId + ", status='" + status + "', totalAmount=" + totalAmount + ", orderDate='" + orderDate + "', orderStatus='" + orderStatus + "'}";
    }
}
