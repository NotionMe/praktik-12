package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import ua.praktik.model.Category;

public interface CategoryRepository {

  /**
   * Save a new category to the database
   *
   * @param category the category to persist
   * @return the generated ID of the new category
   */
  Integer persist(Category category);

  /**
   * Update an existing category
   *
   * @param category the category to update
   * @return true if updated successfully, false if not found
   */
  boolean update(Category category);

  /**
   * Find a category by its ID
   *
   * @param id the category ID
   * @return Optional containing the category if found
   */
  Optional<Category> findById(Integer id);

  /**
   * Get all categories from the database
   *
   * @return list of all categories
   */
  List<Category> findAll();

  /**
   * Delete a category by its ID
   *
   * @param id the category ID
   * @return true if deleted successfully, false if not found
   */
  boolean delete(Integer id);

  /**
   * Find category by name
   *
   * @param name the category name
   * @return Optional containing the category if found
   */
  Optional<Category> findByName(String name);

  /**
   * Find categories by description substring
   *
   * @param description the description substring
   * @return list of matching categories
   */
  List<Category> findByDescription(String description);

  /**
   * Count total number of categories
   *
   * @return count of categories
   */
  int count();
}
