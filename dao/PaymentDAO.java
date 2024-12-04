package dao;
import model.Payment;

public interface PaymentDAO {
    void addPayment(Payment payment) throws Exception;

    Payment getPaymentByOrderId(int orderId) throws Exception;

    void updatePaymentStatus(int id, String status) throws Exception;
}
