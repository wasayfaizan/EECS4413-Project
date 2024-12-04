package dao;

import model.Payment;

import java.sql.*;

public class PaymentDaoImpl implements PaymentDAO {

  static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private String dbPath;

    public PaymentDaoImpl(String dbPath) {
        this.dbPath = dbPath;
    }
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
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
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, payment.getOrderId());
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setString(3, payment.getPaymentMethod());
            preparedStatement.setString(4, payment.getPaymentStatus());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Payment getPaymentByOrderId(int orderId) throws Exception {
        String query = "SELECT * FROM payments WHERE order_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Payment(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("payment_method"),
                        resultSet.getString("payment_status")
                );
            }
        }
        return null;
    }

    @Override
    public void updatePaymentStatus(int id, String status) throws Exception {
        String query = "UPDATE payments SET payment_status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement =