package com.example.solemate.dao;

import com.example.solemate.model.Payment;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDAO {

  static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Value("${spring.datasource.url}")
    private String dbPath;

//    public PaymentDaoImpl(String dbPath) {
//        this.dbPath = dbPath;
//    }
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbPath);
    }

    private void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
    @Override
    public void addPayment(Payment payment) throws Exception {
        String query = "INSERT INTO payments (order_id, amount, payment_method, payment_status) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, payment.getOrderId());
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setString(3, payment.getPaymentMethod());
            preparedStatement.setString(4, payment.getPaymentStatus());
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Payment getPaymentById(int id) throws Exception {
        String query = "SELECT * FROM payments WHERE id = ?";
        Connection connection = null;
        Payment payment = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                payment = new Payment(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("payment_method"),
                        resultSet.getString("payment_status")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return payment;
    }

//    @Override
//    public Payment getPaymentByOrderId(int orderId) throws Exception {
//        String query = "SELECT * FROM payments WHERE order_id = ?";
//        Connection connection = null;
//        try {
//            connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, orderId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                return new Payment(
//                        resultSet.getInt("id"),
//                        resultSet.getInt("order_id"),
//                        resultSet.getDouble("amount"),
//                        resultSet.getString("payment_method"),
//                        resultSet.getString("payment_status")
//                );
//            }
//        }catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            closeConnection(connection);
//        }
//        return null;
//    }

    @Override
    public void updatePaymentStatus(int id, String status) throws Exception {
        String query = "UPDATE payments SET payment_status = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deletePayment(int id) {
        String query = "DELETE FROM payments WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        String query = "SELECT * FROM payments";
        Connection connection = null;
        List<Payment> payments = new ArrayList<>();
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Payment payment = new Payment(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("payment_method"),
                        resultSet.getString("payment_status")
                );
                payments.add(payment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return payments;
    }

    @Override
    public void updatePayment(Payment payment) {
        String query = "UPDATE payments SET order_id = ?, amount = ?, payment_method = ?, payment_status = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, payment.getOrderId());
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setString(3, payment.getPaymentMethod());
            preparedStatement.setString(4, payment.getPaymentStatus());
            preparedStatement.setInt(5, payment.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}