package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.praktik.model.Publisher;

@Component
public class PublisherRepositoryImpl implements PublisherRepository {

  private final JdbcTemplate jdbcTemplate;

  public PublisherRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final RowMapper<Publisher> rowMapper = (rs, i) -> Publisher.builder()
      .id(rs.getInt("id"))
      .name(rs.getString("name"))
      .country(rs.getString("country"))
      .foundedYear(rs.getInt("founded_year"))
      .build();

  @Override
  public Integer persist(Publisher publisher) {
    String sql = "INSERT INTO publishers (name, country, founded_year) VALUES (?, ?, ?)";
    jdbcTemplate.update(
        sql, publisher.getName(), publisher.getCountry(), publisher.getFoundedYear());
    return null;
  }

  @Override
  public boolean update(Publisher publisher) {
    String sql = "UPDATE publishers SET name = ?, country = ?, founded_year = ? WHERE id = ?";
    int rowsAffected = jdbcTemplate.update(
        sql,
        publisher.getName(),
        publisher.getCountry(),
        publisher.getFoundedYear(),
        publisher.getId());
    return rowsAffected > 0;
  }

  @Override
  public Optional<Publisher> findById(Integer id) {
    String sql = "SELECT * FROM publishers WHERE id = ?";
    try {
      Publisher publisher = jdbcTemplate.queryForObject(sql, new Object[] { id }, rowMapper);
      return Optional.ofNullable(publisher);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Publisher> findAll() {
    String sql = "SELECT * FROM publishers";
    return jdbcTemplate.query(sql, rowMapper);
  }

  @Override
  public boolean delete(Integer id) {
    String sql = "DELETE FROM publishers WHERE id = ?";
    int rowsAffected = jdbcTemplate.update(sql, id);
    return rowsAffected > 0;
  }

  @Override
  public Optional<Publisher> findByName(String name) {
    String sql = "SELECT * FROM publishers WHERE name = ?";
    try {
      Publisher publisher = jdbcTemplate.queryForObject(sql, new Object[] { name }, rowMapper);
      return Optional.ofNullable(publisher);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Publisher> findByCountry(String country) {
    String sql = "SELECT * FROM publishers WHERE country LIKE ?";
    return jdbcTemplate.query(sql, new Object[] { "%" + country + "%" }, rowMapper);
  }

  @Override
  public int count() {
    String sql = "SELECT COUNT(*) FROM publishers";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    return count != null ? count : 0;
  }
}
