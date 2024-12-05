package dao;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO {

  static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private String dbPath;

    public CartDaoImpl(String dbPath) {
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
    public void addUser(User user) throws Exception {
        String query = "INSERT INTO users (username, password, email, full_name, address, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFullName());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public User getUserById(int id) throws Exception {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("full_name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number")
                );
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("full_name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number")
                ));
            }
        }
        return users;
    }

    @Override
    public void updateUser(User user) throws Exception {
        String query = "UPDATE users SET username = ?, password = ?, email = ?, full_name = ?, address = ?, phone_number = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFullName());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setInt(7, user.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteUser(int id) throws Exception {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
