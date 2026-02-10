package ua.praktik.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ua.praktik.model.Book;
import ua.praktik.util.DatabaseConfig;

public class BookRepositoryImpl implements BookRepository {

  @Override
  public void insert(Book book) {
    String sql =
        """
                INSERT INTO books (title, author, isbn, publication_year, publisher, price, page_count, image_path)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, book.getTitle());
      ps.setString(2, book.getAuthor());
      ps.setString(3, book.getIsbn());
      ps.setInt(4, book.getPublicationYear());
      ps.setString(5, book.getPublisher());
      ps.setDouble(6, book.getPrice());
      ps.setInt(7, book.getPageCount());
      ps.setString(8, book.getImagePath());

      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void insertBatch(List<Book> books) {
    String sql =
        """
                INSERT INTO books (title, author, isbn, publication_year, publisher, price, page_count, image_path)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      conn.setAutoCommit(false);

      for (Book book : books) {
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getAuthor());
        ps.setString(3, book.getIsbn());
        ps.setInt(4, book.getPublicationYear());
        ps.setString(5, book.getPublisher());
        ps.setDouble(6, book.getPrice());
        ps.setInt(7, book.getPageCount());
        ps.setString(8, book.getImagePath());
        ps.addBatch();
      }

      ps.executeBatch();
      conn.commit();
      conn.setAutoCommit(true);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Book> findById(int id) {
    String sql = "SELECT * FROM books WHERE id = ?";

    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        Book book =
            new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getInt("publication_year"),
                rs.getString("publisher"),
                rs.getDouble("price"),
                rs.getInt("page_count"),
                rs.getString("image_path"));
        return Optional.of(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }

  @Override
  public List<Book> findAll() {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books";

    try (Connection conn = DatabaseConfig.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql)) {

      while (rs.next()) {
        Book book =
            new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getInt("publication_year"),
                rs.getString("publisher"),
                rs.getDouble("price"),
                rs.getInt("page_count"),
                rs.getString("image_path"));
        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public int count() {
    String sql = "SELECT COUNT(*) FROM books";

    try (Connection conn = DatabaseConfig.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql)) {

      if (rs.next()) {
        return rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return 0;
  }

  @Override
  public List<Book> findByTitle(String title) {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books WHERE title LIKE ?";

    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, "%" + title + "%");
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Book book =
            new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getInt("publication_year"),
                rs.getString("publisher"),
                rs.getDouble("price"),
                rs.getInt("page_count"),
                rs.getString("image_path"));
        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public List<Book> findByAuthor(String author) {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books WHERE author LIKE ?";

    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, "%" + author + "%");
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Book book =
            new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getInt("publication_year"),
                rs.getString("publisher"),
                rs.getDouble("price"),
                rs.getInt("page_count"),
                rs.getString("image_path"));
        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public List<Book> findByIsbn(String isbn) {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books WHERE isbn LIKE ?";

    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, "%" + isbn + "%");
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Book book =
            new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getInt("publication_year"),
                rs.getString("publisher"),
                rs.getDouble("price"),
                rs.getInt("page_count"),
                rs.getString("image_path"));
        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public List<Book> findByPublicationYear(int year) {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books WHERE publication_year = ?";

    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, year);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Book book =
            new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getInt("publication_year"),
                rs.getString("publisher"),
                rs.getDouble("price"),
                rs.getInt("page_count"),
                rs.getString("image_path"));
        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public List<Book> findByPublisher(String publisher) {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books WHERE publisher LIKE ?";

    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, "%" + publisher + "%");
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Book book =
            new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getInt("publication_year"),
                rs.getString("publisher"),
                rs.getDouble("price"),
                rs.getInt("page_count"),
                rs.getString("image_path"));
        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public List<Book> findByPrice(double price) {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books WHERE price = ?";

    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setDouble(1, price);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Book book =
            new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getInt("publication_year"),
                rs.getString("publisher"),
                rs.getDouble("price"),
                rs.getInt("page_count"),
                rs.getString("image_path"));
        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public List<Book> findByPageCount(int pageCount) {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books WHERE page_count = ?";

    try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, pageCount);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Book book =
            new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getInt("publication_year"),
                rs.getString("publisher"),
                rs.getDouble("price"),
                rs.getInt("page_count"),
                rs.getString("image_path"));
        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }
}
