package com.example.solemate.dao;
import com.example.solemate.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDAO {


  static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private String dbPath;


    public ProductDaoImpl(String dbPath) {
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
    public void addProduct(Product product) throws Exception {
        String query = "INSERT INTO Products (name, description, price, brand_id, image_url) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getBrandId());
            preparedStatement.setString(5, product.getImageUrl());
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Product getProductById(int id) throws Exception {
        String query = "SELECT * FROM Products WHERE id = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("brand_id"),
                        resultSet.getString("image_url")
                );
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products";
        Connection connection = null;
        try{ 
             connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("brand_id"),
                        resultSet.getString("image_url")
                ));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        String query = "UPDATE Products SET name = ?, description = ?, price = ?, brand_id = ?, image_url = ? WHERE id = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getBrandId());
            preparedStatement.setString(5, product.getImageUrl());
            preparedStatement.setInt(6, product.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void deleteProduct(int id) throws Exception {
        String query = "DELETE FROM Products WHERE id = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
