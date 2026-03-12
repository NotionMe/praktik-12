package ua.praktik.repository;

import java.util.List;
import java.util.Optional;
import ua.praktik.model.Publisher;

public interface PublisherRepository {

  Integer persist(Publisher publisher);

  boolean update(Publisher publisher);

  Optional<Publisher> findById(Integer id);

  List<Publisher> findAll();

  boolean delete(Integer id);

  Optional<Publisher> findByName(String name);

  List<Publisher> findByCountry(String country);

  int count();
}
