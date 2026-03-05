package ua.praktik.util;

import java.util.ArrayList;
import java.util.List;
import net.datafaker.Faker;
import ua.praktik.model.Book;
import ua.praktik.repository.BookRepository;
import ua.praktik.repository.BookRepositoryImpl;

public class DataGenerator {

  private static final Faker faker = new Faker();

  public static void generateAndInsertBooks(int count) {
    BookRepository bookRepository = new BookRepositoryImpl();
    List<Book> books = new ArrayList<>();

    System.out.println("Генерація " + count + " книг...");

    for (int i = 0; i < count; i++) {
      Book book = new Book(
          faker.book().title(),
          faker.book().author(),
          faker.code().isbn13(),
          faker.number().numberBetween(1900, 2024),
          faker.book().publisher(),
          faker.number().randomDouble(2, 5, 100),
          faker.number().numberBetween(50, 1000),
          "https://picsum.photos/100/150?random=" + faker.number().numberBetween(1, 1000));
      books.add(book);
    }

    System.out.println("Вставка даних в базу...");
    bookRepository.insertBatch(books);

    int totalBooks = bookRepository.count();
    System.out.println("Успішно додано " + count + " книг!");
    System.out.println("Всього книг в базі: " + totalBooks);
  }

  public static void main(String[] args) {
    // DatabaseInit.initBooks();

    generateAndInsertBooks(50);

    BookRepository bookRepository = new BookRepositoryImpl();
    List<Book> books = bookRepository.findAll();

    System.out.println("\nПерші 5 книг з бази:");
    books.stream().limit(5).forEach(System.out::println);
  }
}
