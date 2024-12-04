package dao;

import model.Order;
import java.util.List;

public interface OrderDAO {
    void createOrder(Order order) throws Exception;

    Order getOrderById(int id) throws Exception;

    List<Order> getOrdersByUserId(int userId) throws Exception;

    void updateOrderStatus(int id, String status) throws Exception;

    void deleteOrder(int id) throws Exception;
}
