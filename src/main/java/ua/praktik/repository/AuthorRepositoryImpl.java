package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.praktik.model.Author;

@Component
public class AuthorRepositoryImpl implements AuthorRepository {

  private final JdbcTemplate jdbcTemplate;

  public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final RowMapper<Author> rowMapper = (rs, i) -> Author.builder()
      .id(rs.getInt("id"))
      .name(rs.getString("name"))
      .country(rs.getString("country"))
      .birthYear(rs.getInt("birth_year"))
      .build();

  @Override
  public Integer persist(Author author) {
    String sql = "INSERT INTO authors (name, country, birth_year) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, author.getName(), author.getCountry(), author.getBirthYear());
    return null;
  }

  @Override
  public boolean update(Author author) {
    String sql = "UPDATE authors SET name = ?, country = ?, birth_year = ? WHERE id = ?";
    int rowsAffected = jdbcTemplate.update(
        sql, author.getName(), author.getCountry(), author.getBirthYear(), author.getId());
    return rowsAffected > 0;
  }

  @Override
  public Optional<Author> findById(Integer id) {
    String sql = "SELECT * FROM authors WHERE id = ?";
    try {
      Author author = jdbcTemplate.queryForObject(sql, new Object[] { id }, rowMapper);
      return Optional.ofNullable(author);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Author> findAll() {
    String sql = "SELECT * FROM authors";
    return jdbcTemplate.query(sql, rowMapper);
  }

  @Override
  public boolean delete(Integer id) {
    String sql = "DELETE FROM authors WHERE id = ?";
    int rowsAffected = jdbcTemplate.update(sql, id);
    return rowsAffected > 0;
  }

  @Override
  public Optional<Author> findByName(String name) {
    String sql = "SELECT * FROM authors WHERE name = ?";
    try {
      Author author = jdbcTemplate.queryForObject(sql, new Object[] { name }, rowMapper);
      return Optional.ofNullable(author);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Author> findByCountry(String country) {
    String sql = "SELECT * FROM authors WHERE country LIKE ?";
    return jdbcTemplate.query(sql, new Object[] { "%" + country + "%" }, rowMapper);
  }

  @Override
  public int count() {
    String sql = "SELECT COUNT(*) FROM authors";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    return count != null ? count : 0;
  }
}
