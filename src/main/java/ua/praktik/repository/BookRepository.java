package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import ua.praktik.model.Book;

public interface BookRepository {

  Integer persist(Book book);

  boolean update(Book book);

  Optional<Book> findById(Integer id);

  List<Book> findAll();

  boolean delete(Integer id);

  int count();

  List<Book> findByTitle(String title);

  List<Book> findByAuthorId(Integer authorId);

  List<Book> findByIsbn(String isbn);

  List<Book> findByPublicationYear(int year);

  List<Book> findByPublisherId(Integer publisherId);

  List<Book> findByPrice(double price);

  List<Book> findByPageCount(int pageCount);
}
