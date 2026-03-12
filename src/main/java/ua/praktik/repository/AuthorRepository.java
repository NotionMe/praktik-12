package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import ua.praktik.model.Author;

public interface AuthorRepository {

  /**
   * Save a new author to the database
   *
   * @param author the author to persist
   * @return the generated ID of the new author
   */
  Integer persist(Author author);

  /**
   * Update an existing author
   *
   * @param author the author to update
   * @return true if updated successfully, false if not found
   */
  boolean update(Author author);

  /**
   * Find an author by its ID
   *
   * @param id the author ID
   * @return Optional containing the author if found
   */
  Optional<Author> findById(Integer id);

  /**
   * Get all authors from the database
   *
   * @return list of all authors
   */
  List<Author> findAll();

  /**
   * Delete an author by its ID
   *
   * @param id the author ID
   * @return true if deleted successfully, false if not found
   */
  boolean delete(Integer id);

  /**
   * Find author by name
   *
   * @param name the author name
   * @return Optional containing the author if found
   */
  Optional<Author> findByName(String name);

  /**
   * Find authors by country
   *
   * @param country the country
   * @return list of authors from the country
   */
  List<Author> findByCountry(String country);

  /**
   * Count total number of authors
   *
   * @return count of authors
   */
  int count();
}
