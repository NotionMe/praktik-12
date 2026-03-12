package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import ua.praktik.model.Author;

public interface AuthorRepository {

  Integer persist(Author author);

  boolean update(Author author);

  Optional<Author> findById(Integer id);

  List<Author> findAll();

  boolean delete(Integer id);

  Optional<Author> findByName(String name);

  List<Author> findByCountry(String country);

  int count();
}
