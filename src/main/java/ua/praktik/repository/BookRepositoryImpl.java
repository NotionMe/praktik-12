package ua.praktik.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.praktik.model.Book;

@Component
public class BookRepositoryImpl implements BookRepository {

  private static JdbcTemplate jdbcTemplate;

  public BookRepositoryImpl() {
    jdbcTemplate = new JdbcTemplate();
  }

  @Override
  public void insert(Book book) {
    String sql = """
        INSERT INTO books (title, author, isbn, publication_year, publisher, price, page_count, image_path)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
    jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublicationYear(),
        book.getPublisher(), book.getPrice(), book.getPageCount(), book.getImagePath());

  }

  @Override
  public void insertBatch(List<Book> books) {
    String sql = """
        INSERT INTO books (title, author, isbn, publication_year, publisher, price, page_count, image_path)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
    for (Book book : books) {
      jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublicationYear(),
          book.getPublisher(), book.getPrice(), book.getPageCount(), book.getImagePath());
    }

  }

  @Override
  public Optional<Book> findById(int id) {
    String sql = "SELECT * FROM books WHERE id = ?";
    return Optional.of(jdbcTemplate.queryForObject(sql, new Object[] { id }, rowMapper));

  }

  private final RowMapper<Book> rowMapper = (rs, i) -> {
    Book book = new Book(
        rs.getInt("id"),
        rs.getString("title"),
        rs.getString("author"),
        rs.getString("isbn"),
        rs.getInt("publication_year"),
        rs.getString("publisher"),
        rs.getDouble("price"),
        rs.getInt("page_count"),
        rs.getString("image_path"));

    return book;
  };

  @Override
  public List<Book> findAll() {
    String sql = "SELECT * FROM books";
    return jdbcTemplate.query(sql, rowMapper);
  }

  @Override
  public int count() {
    String sql = "SELECT COUNT(*) FROM books";
    return jdbcTemplate.queryForObject(sql, Integer.class);
  }

  @Override
  public List<Book> findByTitle(String title) {
    String sql = "SELECT * FROM books WHERE title LIKE ?";
    return jdbcTemplate.query(sql, new Object[] { "%" + title + "%" }, rowMapper);
  }

  @Override
  public List<Book> findByAuthor(String author) {
    String sql = "SELECT * FROM books WHERE author LIKE ?";
    return jdbcTemplate.query(sql, new Object[] { "%" + author + "%" }, rowMapper);
  }

  @Override
  public List<Book> findByIsbn(String isbn) {
    String sql = "SELECT * FROM books WHERE isbn LIKE ?";
    return jdbcTemplate.query(sql, new Object[] { "%" + isbn + "%" }, rowMapper);
  }

  @Override
  public List<Book> findByPublicationYear(int year) {
    String sql = "SELECT * FROM books WHERE publication_year = ?";

    return jdbcTemplate.query(sql, new Object[] { year }, rowMapper);
  }

  @Override
  public List<Book> findByPublisher(String publisher) {
    String sql = "SELECT * FROM books WHERE publisher LIKE ?";
    return jdbcTemplate.query(sql, new Object[] { "%" + publisher + "%" }, rowMapper);
  }

  @Override
  public List<Book> findByPrice(double price) {
    String sql = "SELECT * FROM books WHERE price = ?";
    return jdbcTemplate.query(sql, new Object[] { price }, rowMapper);
  }

  @Override
  public List<Book> findByPageCount(int pageCount) {
    String sql = "SELECT * FROM books WHERE page_count = ?";
    return jdbcTemplate.query(sql, new Object[] { pageCount }, rowMapper);
  }
}
