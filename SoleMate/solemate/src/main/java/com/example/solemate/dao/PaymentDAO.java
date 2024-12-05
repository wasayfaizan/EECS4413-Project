package com.example.solemate.dao;
import com.example.solemate.model.Payment;

public interface PaymentDAO {
    void addPayment(Payment payment) throws Exception;

    Payment getPaymentByOrderId(int orderId) throws Exception;

    void updatePaymentStatus(int id, String status) throws Exception;
}
