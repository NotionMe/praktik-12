package ua.praktik.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.praktik.model.User;
import ua.praktik.util.DatabaseConfig;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void save(User user) throws IOException, SQLException {
        String userName = user.getUserName();
        String password = user.getPassword();
        int id = user.getId();

        appendProfile(id, userName, password);
    }

    @Override
    public User findByUserName(String userName) throws SQLException {
        String sql = "SELECT * FROM users WHERE name = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userName);
            User user;
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    return user = new User(id, name, password);
                }
            }
        }
        return null;
    }

    @Override
    public User findById(int searchId) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, searchId);
            User user;
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String name = rs.getString("name");
                    String password = rs.getString("password");

                    return user = new User(id, name, password);
                }
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM users")) {

            User user;
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");

                user = new User(id, name, password);
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public void update(int id, String userName, String password) throws IOException, SQLException {
        String sql = "UPDATE users SET name = ?, password = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setInt(3, id);

            ps.executeUpdate();
            System.out.println("Update saved successfully. ");
        }
    }

    @Override
    public void delete(int id) throws IOException, SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Delete successfully!");
        }
    }

    private void appendProfile(int id, String name, String password) throws SQLException {
        String sql = "INSERT INTO users (id, name, password) VALUES (? ,?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.executeUpdate();
            System.out.println("Data saved successfully.");
        }
    }
}
