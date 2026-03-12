package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.praktik.model.Book;

@Component
public class BookRepositoryImpl implements BookRepository {

  private final JdbcTemplate jdbcTemplate;

  public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final RowMapper<Book> rowMapper =
      (rs, i) ->
          Book.builder()
              .id(rs.getInt("id"))
              .title(rs.getString("title"))
              .authorId(rs.getInt("author_id"))
              .isbn(rs.getString("isbn"))
              .publicationYear(rs.getInt("publication_year"))
              .publisherId(rs.getInt("publisher_id"))
              .categoryId(rs.getInt("category_id"))
              .price(rs.getDouble("price"))
              .pageCount(rs.getInt("page_count"))
              .imagePath(rs.getString("image_path"))
              .build();

  @Override
  public Integer persist(Book book) {
    String sql =
        """
        INSERT INTO books (title, author_id, isbn, publication_year, publisher_id, category_id, price, page_count, image_path)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
    jdbcTemplate.update(
        sql,
        book.getTitle(),
        book.getAuthorId(),
        book.getIsbn(),
        book.getPublicationYear(),
        book.getPublisherId(),
        book.getCategoryId(),
        book.getPrice(),
        book.getPageCount(),
        book.getImagePath());
    return null;
  }

  @Override
  public boolean update(Book book) {
    String sql =
        """
        UPDATE books
        SET title = ?, author_id = ?, isbn = ?, publication_year = ?, publisher_id = ?,
            category_id = ?, price = ?, page_count = ?, image_path = ?
        WHERE id = ?
        """;
    int rowsAffected =
        jdbcTemplate.update(
            sql,
            book.getTitle(),
            book.getAuthorId(),
            book.getIsbn(),
            book.getPublicationYear(),
            book.getPublisherId(),
            book.getCategoryId(),
            book.getPrice(),
            book.getPageCount(),
            book.getImagePath(),
            book.getId());
    return rowsAffected > 0;
  }

  @Override
  public Optional<Book> findById(Integer id) {
    String sql = "SELECT * FROM books WHERE id = ?";
    try {
      Book book = jdbcTemplate.queryForObject(sql, new Object[] {id}, rowMapper);
      return Optional.ofNullable(book);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Book> findAll() {
    String sql = "SELECT * FROM books";
    return jdbcTemplate.query(sql, rowMapper);
  }

  @Override
  public boolean delete(Integer id) {
    String sql = "DELETE FROM books WHERE id = ?";
    int rowsAffected = jdbcTemplate.update(sql, id);
    return rowsAffected > 0;
  }

  @Override
  public int count() {
    String sql = "SELECT COUNT(*) FROM books";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    return count != null ? count : 0;
  }

  @Override
  public List<Book> findByTitle(String title) {
    String sql = "SELECT * FROM books WHERE title LIKE ?";
    return jdbcTemplate.query(sql, new Object[] {"%" + title + "%"}, rowMapper);
  }

  @Override
  public List<Book> findByAuthorId(Integer authorId) {
    String sql = "SELECT * FROM books WHERE author_id = ?";
    return jdbcTemplate.query(sql, new Object[] {authorId}, rowMapper);
  }

  @Override
  public List<Book> findByIsbn(String isbn) {
    String sql = "SELECT * FROM books WHERE isbn LIKE ?";
    return jdbcTemplate.query(sql, new Object[] {"%" + isbn + "%"}, rowMapper);
  }

  @Override
  public List<Book> findByPublicationYear(int year) {
    String sql = "SELECT * FROM books WHERE publication_year = ?";
    return jdbcTemplate.query(sql, new Object[] {year}, rowMapper);
  }

  @Override
  public List<Book> findByPublisherId(Integer publisherId) {
    String sql = "SELECT * FROM books WHERE publisher_id = ?";
    return jdbcTemplate.query(sql, new Object[] {publisherId}, rowMapper);
  }

  @Override
  public List<Book> findByPrice(double price) {
    String sql = "SELECT * FROM books WHERE price = ?";
    return jdbcTemplate.query(sql, new Object[] {price}, rowMapper);
  }

  @Override
  public List<Book> findByPageCount(int pageCount) {
    String sql = "SELECT * FROM books WHERE page_count = ?";
    return jdbcTemplate.query(sql, new Object[] {pageCount}, rowMapper);
  }
}
