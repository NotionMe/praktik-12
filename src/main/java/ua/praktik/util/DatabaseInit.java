package ua.praktik.util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInit {

    public static void initUser() {

        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                        id INT PRIMARY KEY,
                        name VARCHAR(255),
                        password VARCHAR(255)
                    );
                """;

        try (Connection conn = DatabaseConfig.getConnection();
                Statement st = conn.createStatement()) {

            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initReview() {

        String sql = """
                CREATE TABLE IF NOT EXISTS reviewBooks (
                        id INT PRIMARY KEY,
                        theater_name VARCHAR(20),
                        author VARCHAR(20),
                        message VARCHAR(20),
                        rating INT(11),
                        date VARCHAR(20)
                    );
                """;

        try (Connection conn = DatabaseConfig.getConnection();
                Statement st = conn.createStatement()) {

            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initTheaters() {

        String sql = """
                CREATE TABLE IF NOT EXISTS theaters (
                        id INT PRIMARY KEY,
                        theaters_name VARCHAR(20),
                        show_name VARCHAR(20),
                        rating INT(11)
                    );
                """;

        try (Connection conn = DatabaseConfig.getConnection();
                Statement st = conn.createStatement()) {

            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}