package dao;

import model.Cart;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDAO {

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
    public Cart getCartByUserId(int userId) throws Exception {
        String query = "SELECT c.id AS cart_id, p.* FROM carts c " +
                "JOIN cart_products cp ON c.id = cp.cart_id " +
                "JOIN products p ON cp.product_id = p.id WHERE c.user_id = ?";
        Cart cart = new Cart();
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (cart.getId() == 0) {
                    cart.setId(resultSet.getInt("cart_id"));
                    cart.setUserId(userId);
                }
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("brand_id"),
                        resultSet.getString("image_url")
                ));
            }
        }

        cart.setProducts(products);
        return cart;
    }

    @Override
    public void addProductToCart(int cartId, int productId) throws Exception {
        String query = "INSERT INTO cart_products (cart_id, product_id) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void removeProductFromCart(int cartId, int productId) throws Exception {
        String query = "DELETE FROM cart_products WHERE cart_id = ? AND product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        }
    }
}
