package ua.praktik.util;

// public class DatabaseInit {

//   public static void initUser() {

//     String sql =
//         """
//                 CREATE TABLE IF NOT EXISTS users (
//                         id INT PRIMARY KEY,
//                         name VARCHAR(255),
//                         password VARCHAR(255)
//                     );
//                 """;

//     try (Connection conn = DatabaseConfig.getConnection();
//         Statement st = conn.createStatement()) {

//       st.execute(sql);
//     } catch (Exception e) {
//       e.printStackTrace();
//     }
//   }

//   public static void initBooks() {

//     String sql =
//         """
//                 CREATE TABLE IF NOT EXISTS books (
//                         id INT PRIMARY KEY AUTO_INCREMENT,
//                         title VARCHAR(255) NOT NULL,
//                         author VARCHAR(255) NOT NULL,
//                         isbn VARCHAR(20),
//                         publication_year INT,
//                         publisher VARCHAR(255),
//                         price DECIMAL(10, 2),
//                         page_count INT,
//                         image_path VARCHAR(500)
//                     );
//                 """;

//     try (Connection conn = DatabaseConfig.getConnection();
//         Statement st = conn.createStatement()) {

//       st.execute(sql);
//     } catch (Exception e) {
//       e.printStackTrace();
//     }
//   }
// }
