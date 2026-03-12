package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import ua.praktik.model.Book;

public interface BookRepository {

  /**
   * Save a new book to the database
   *
   * @param book the book to persist
   * @return the generated ID of the new book
   */
  Integer persist(Book book);

  /**
   * Update an existing book
   *
   * @param book the book to update
   * @return true if updated successfully, false if not found
   */
  boolean update(Book book);

  /**
   * Find a book by its ID
   *
   * @param id the book ID
   * @return Optional containing the book if found
   */
  Optional<Book> findById(Integer id);

  /**
   * Get all books from the database
   *
   * @return list of all books
   */
  List<Book> findAll();

  /**
   * Delete a book by its ID
   *
   * @param id the book ID
   * @return true if deleted successfully, false if not found
   */
  boolean delete(Integer id);

  /**
   * Count total number of books
   *
   * @return count of books
   */
  int count();

  List<Book> findByTitle(String title);

  List<Book> findByAuthorId(Integer authorId);

  List<Book> findByIsbn(String isbn);

  List<Book> findByPublicationYear(int year);

  List<Book> findByPublisherId(Integer publisherId);

  List<Book> findByPrice(double price);

  List<Book> findByPageCount(int pageCount);
}
