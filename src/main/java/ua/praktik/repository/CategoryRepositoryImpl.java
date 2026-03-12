package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.praktik.model.Category;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {

  private final JdbcTemplate jdbcTemplate;

  public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final RowMapper<Category> rowMapper = (rs, i) -> Category.builder()
      .id(rs.getInt("id"))
      .name(rs.getString("name"))
      .description(rs.getString("description"))
      .build();

  @Override
  public Integer persist(Category category) {
    String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
    jdbcTemplate.update(sql, category.getName(), category.getDescription());
    return null;
  }

  @Override
  public boolean update(Category category) {
    String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";
    int rowsAffected = jdbcTemplate.update(sql, category.getName(), category.getDescription(), category.getId());
    return rowsAffected > 0;
  }

  @Override
  public Optional<Category> findById(Integer id) {
    String sql = "SELECT * FROM categories WHERE id = ?";
    try {
      Category category = jdbcTemplate.queryForObject(sql, new Object[] { id }, rowMapper);
      return Optional.ofNullable(category);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Category> findAll() {
    String sql = "SELECT * FROM categories";
    return jdbcTemplate.query(sql, rowMapper);
  }

  @Override
  public boolean delete(Integer id) {
    String sql = "DELETE FROM categories WHERE id = ?";
    int rowsAffected = jdbcTemplate.update(sql, id);
    return rowsAffected > 0;
  }

  @Override
  public Optional<Category> findByName(String name) {
    String sql = "SELECT * FROM categories WHERE name = ?";
    try {
      Category category = jdbcTemplate.queryForObject(sql, new Object[] { name }, rowMapper);
      return Optional.ofNullable(category);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Category> findByDescription(String description) {
    String sql = "SELECT * FROM categories WHERE description LIKE ?";
    return jdbcTemplate.query(sql, new Object[] { "%" + description + "%" }, rowMapper);
  }

  @Override
  public int count() {
    String sql = "SELECT COUNT(*) FROM categories";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    return count != null ? count : 0;
  }
}
