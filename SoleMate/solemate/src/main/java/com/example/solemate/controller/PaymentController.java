package com.example.solemate.controller;

import com.example.solemate.model.Payment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private List<Payment> payments = new ArrayList<>(); // Temporary in-memory storage

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable int id) {
        return payments.stream()
                .filter(payment -> payment.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/by-order/{orderId}")
    public List<Payment> getPaymentsByOrderId(@PathVariable int orderId) {
        List<Payment> results = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getOrderId() == orderId) {
                results.add(payment);
            }
        }
        return results;
    }


    @GetMapping
    public List<Payment> getAllPayments() {
        return payments;
    }

    @PostMapping
    public String createPayment(@RequestBody Payment payment) {
        payments.add(payment);
        return "Payment created successfully!";
    }

    @PutMapping("/{id}")
    public String updatePayment(@PathVariable int id, @RequestBody Payment updatedPayment) {
        Payment payment = payments.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if (payment != null) {
            payment.setAmount(updatedPayment.getAmount());
            payment.setPaymentMethod(updatedPayment.getPaymentMethod());
            payment.setPaymentStatus(updatedPayment.getPaymentStatus());
            return "Payment updated successfully!";
        }
        return "Payment not found!";
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable int id) {
        boolean removed = payments.removeIf(payment -> payment.getId() == id);
        return removed ? "Payment deleted successfully!" : "Payment not found!";
    }
}
