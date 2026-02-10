package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import ua.praktik.model.Book;

public interface BookRepository {
  void insert(Book book);

  void insertBatch(List<Book> books);

  List<Book> findAll();

  int count();

  Optional<Book> findById(int id);

  List<Book> findByTitle(String title);

  List<Book> findByAuthor(String author);

  List<Book> findByIsbn(String isbn);

  List<Book> findByPublicationYear(int year);

  List<Book> findByPublisher(String publisher);

  List<Book> findByPrice(double price);

  List<Book> findByPageCount(int pageCount);
}
