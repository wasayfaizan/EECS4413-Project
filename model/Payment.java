package model;

public class Payment {
    private int id;
    private int orderId;
    private double amount;
    private String paymentMethod;  // E.g., Credit Card, PayPal, etc.
    private String paymentStatus;  // E.g., Pending, Completed, Failed

    // Default constructor
    public Payment() {}

    // Parameterized constructor
    public Payment(int id, int orderId, double amount, String paymentMethod, String paymentStatus) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Payment{id=" + id + ", orderId=" + orderId + ", amount=" + amount + ", paymentMethod='" + paymentMethod + "', paymentStatus='" + paymentStatus + "'}";
    }
}
