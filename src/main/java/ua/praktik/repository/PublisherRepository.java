package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import ua.praktik.model.Publisher;

public interface PublisherRepository {

  /**
   * Save a new publisher to the database
   *
   * @param publisher the publisher to persist
   * @return the generated ID of the new publisher
   */
  Integer persist(Publisher publisher);

  /**
   * Update an existing publisher
   *
   * @param publisher the publisher to update
   * @return true if updated successfully, false if not found
   */
  boolean update(Publisher publisher);

  /**
   * Find a publisher by its ID
   *
   * @param id the publisher ID
   * @return Optional containing the publisher if found
   */
  Optional<Publisher> findById(Integer id);

  /**
   * Get all publishers from the database
   *
   * @return list of all publishers
   */
  List<Publisher> findAll();

  /**
   * Delete a publisher by its ID
   *
   * @param id the publisher ID
   * @return true if deleted successfully, false if not found
   */
  boolean delete(Integer id);

  /**
   * Find publisher by name
   *
   * @param name the publisher name
   * @return Optional containing the publisher if found
   */
  Optional<Publisher> findByName(String name);

  /**
   * Find publishers by country
   *
   * @param country the country
   * @return list of publishers from the country
   */
  List<Publisher> findByCountry(String country);

  /**
   * Count total number of publishers
   *
   * @return count of publishers
   */
  int count();
}
