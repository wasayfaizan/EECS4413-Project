package com.example.solemate.controller;

import com.example.solemate.dao.PaymentDAO;
import com.example.solemate.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentDAO paymentDAO;


    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable int id) {
        try {
            return paymentDAO.getPaymentById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping
    public List<Payment> getAllPayments() {
        try {
            return paymentDAO.getAllPayments();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping
    public String createPayment(@RequestBody Payment payment) {
        try {
            paymentDAO.addPayment(payment);
            return "Payment created successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating payment: " + e.getMessage();
        }
    }

    @PutMapping("/{id}")
    public String updatePayment(@PathVariable int id, @RequestBody Payment updatedPayment) {
        try {
            Payment payment = paymentDAO.getPaymentById(id);
            if (payment != null) {
                payment.setAmount(updatedPayment.getAmount());
                payment.setPaymentMethod(updatedPayment.getPaymentMethod());
                payment.setPaymentStatus(updatedPayment.getPaymentStatus());
                paymentDAO.updatePayment(payment);
                return "Payment updated successfully!";
            }
            return "Payment not found!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating payment: " + e.getMessage();
        }
    }

    @PutMapping("/{id}/status")
    public String updatePaymentStatus(@PathVariable int id, @RequestParam String status) {
        try {
            paymentDAO.updatePaymentStatus(id, status);
            return "Payment status updated successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating payment status: " + e.getMessage();
        }
    }


    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable int id) {
        try {
            paymentDAO.deletePayment(id);
            return "Payment deleted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting payment: " + e.getMessage();
        }
    }
}
