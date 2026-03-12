package ua.praktik.util;

import java.util.ArrayList;
import java.util.List;
import net.datafaker.Faker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.praktik.model.Book;
import ua.praktik.repository.BookRepository;

public class DataGenerator {

  private static final Faker faker = new Faker();

  public static void generateAndInsertBooks(BookRepository bookRepository, int count) {
    List<Book> books = new ArrayList<>();

    System.out.println("Генерація " + count + " книг...");

    for (int i = 0; i < count; i++) {
      Book book =
          Book.builder()
              .title(faker.book().title())
              .authorId(faker.number().numberBetween(1, 4))
              .isbn(faker.code().isbn13())
              .publicationYear(faker.number().numberBetween(1900, 2024))
              .publisherId(faker.number().numberBetween(1, 4))
              .categoryId(faker.number().numberBetween(1, 4))
              .price(faker.number().randomDouble(2, 5, 100))
              .pageCount(faker.number().numberBetween(50, 1000))
              .imagePath(
                  "https://picsum.photos/100/150?random=" + faker.number().numberBetween(1, 1000))
              .build();
      books.add(book);
    }

    System.out.println("Вставка даних в базу...");
    for (Book book : books) {
      bookRepository.persist(book);
    }

    int totalBooks = bookRepository.count();
    System.out.println("Успішно додано " + count + " книг!");
    System.out.println("Всього книг в базі: " + totalBooks);
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx =
        new AnnotationConfigApplicationContext(AppConfig.class);
    BookRepository bookRepository = ctx.getBean(BookRepository.class);

    generateAndInsertBooks(bookRepository, 50);

    List<Book> books = bookRepository.findAll();

    System.out.println("\nПерші 5 книг з бази:");
    books.stream().limit(5).forEach(System.out::println);
  }
}
