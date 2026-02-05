package ua.praktik.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import ua.praktik.model.User;

public interface UserRepository {
    void save(User user) throws IOException, SQLException;
    User findByUserName(String userName) throws SQLException;
    User findById(int id) throws SQLException;
    List<User> findAll() throws SQLException;
    void update(int id, String userName, String password) throws IOException, SQLException;
    void delete(int id) throws IOException, SQLException;
}
