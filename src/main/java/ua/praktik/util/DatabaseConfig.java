package ua.praktik.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

  private static String url = "jdbc:mysql://localhost:3306/notiondb";
  private static String user = "notion";
  private static String pass = "255455";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, pass);
  }
}
