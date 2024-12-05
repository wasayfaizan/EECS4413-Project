package dao;
import model.User;
import java.util.List;

public interface UserDAO {
    void addUser(User user) throws Exception;

    User getUserById(int id) throws Exception;

    List<User> getAllUsers() throws Exception;

    void updateUser(User user) throws Exception;

    void deleteUser(int id) throws Exception;
}
