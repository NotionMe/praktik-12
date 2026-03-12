package ua.praktik.model;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Book Entity Validation Tests")
class BookValidationTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid Book should pass validation")
  void testValidBook() {
    Book book = Book.builder()
        .id(1)
        .title("Harry Potter")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .categoryId(1)
        .price(15.99)
        .pageCount(223)
        .imagePath("/images/harry-potter.jpg")
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertTrue(violations.isEmpty(), "Valid book should not have violations");
  }

  @Test
  @DisplayName("Book with blank title should fail validation")
  void testBlankTitle() {
    Book book = Book.builder()
        .id(1)
        .title("")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with blank title should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Title cannot be empty")));
  }

  @Test
  @DisplayName("Book with null title should fail validation")
  void testNullTitle() {
    Book book = Book.builder()
        .id(1)
        .title(null)
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with null title should fail");
  }

  @Test
  @DisplayName("Book with null authorId should fail validation")
  void testNullAuthorId() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(null)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with null authorId should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Author ID cannot be null")));
  }

  @Test
  @DisplayName("Book with negative authorId should fail validation")
  void testNegativeAuthorId() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(-1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with negative authorId should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Author ID must be positive")));
  }

  @Test
  @DisplayName("Book with zero authorId should fail validation")
  void testZeroAuthorId() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(0)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with zero authorId should fail");
  }

  @Test
  @DisplayName("Book with invalid ISBN should fail validation")
  void testInvalidISBN() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(1)
        .isbn("invalid-isbn")
        .publicationYear(1997)
        .publisherId(1)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with invalid ISBN should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("ISBN format is invalid")));
  }

  @Test
  @DisplayName("Book with null publisherId should fail validation")
  void testNullPublisherId() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(null)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with null publisherId should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Publisher ID cannot be null")));
  }

  @Test
  @DisplayName("Book with negative publisherId should fail validation")
  void testNegativePublisherId() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(-5)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with negative publisherId should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Publisher ID must be positive")));
  }

  @Test
  @DisplayName("Book with year less than 1000 should fail validation")
  void testInvalidPublicationYear() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(500)
        .publisherId(1)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with year < 1000 should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Publication year must be at least 1000")));
  }

  @Test
  @DisplayName("Book with zero price should fail validation")
  void testZeroPrice() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .price(0.0)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with zero price should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Price must be greater than 0")));
  }

  @Test
  @DisplayName("Book with negative price should fail validation")
  void testNegativePrice() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .price(-10.0)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with negative price should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Price must be greater than 0")));
  }

  @Test
  @DisplayName("Book with zero pageCount should fail validation")
  void testZeroPageCount() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .price(15.99)
        .pageCount(0)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with zero pageCount should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Page count must be positive")));
  }

  @Test
  @DisplayName("Book with negative pageCount should fail validation")
  void testNegativePageCount() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .price(15.99)
        .pageCount(-100)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with negative pageCount should fail");
  }

  @Test
  @DisplayName("Book with negative categoryId should fail validation")
  void testNegativeCategoryId() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .categoryId(-5)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertFalse(violations.isEmpty(), "Book with negative categoryId should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Category ID must be positive")));
  }

  @Test
  @DisplayName("Book with null categoryId should pass validation (optional field)")
  void testNullCategoryId() {
    Book book = Book.builder()
        .id(1)
        .title("Valid Title")
        .authorId(1)
        .isbn("978-0747532699")
        .publicationYear(1997)
        .publisherId(1)
        .categoryId(null)
        .price(15.99)
        .pageCount(223)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    assertTrue(violations.isEmpty(), "Book with null categoryId should pass (optional)");
  }
}
