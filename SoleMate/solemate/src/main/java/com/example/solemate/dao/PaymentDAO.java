package com.example.solemate.dao;
import com.example.solemate.model.Payment;

import java.util.List;

public interface PaymentDAO {
    void addPayment(Payment payment) throws Exception;

    Payment getPaymentById(int orderId) throws Exception;

    void updatePaymentStatus(int id, String status) throws Exception;

    void deletePayment(int id);

    List<Payment> getAllPayments();

    void updatePayment(Payment payment);
}
