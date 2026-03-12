package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import ua.praktik.model.Category;

public interface CategoryRepository {

  Integer persist(Category category);

  boolean update(Category category);

  Optional<Category> findById(Integer id);

  List<Category> findAll();

  boolean delete(Integer id);

  Optional<Category> findByName(String name);

  List<Category> findByDescription(String description);

  int count();
}
