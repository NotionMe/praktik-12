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

@DisplayName("Author Entity Validation Tests")
class AuthorValidationTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid Author should pass validation")
  void testValidAuthor() {
    Author author = Author.builder()
        .id(1)
        .name("J.K. Rowling")
        .country("United Kingdom")
        .birthYear(1965)
        .build();

    Set<ConstraintViolation<Author>> violations = validator.validate(author);
    assertTrue(violations.isEmpty(), "Valid author should not have violations");
  }

  @Test
  @DisplayName("Author with blank name should fail validation")
  void testBlankName() {
    Author author = Author.builder()
        .id(1)
        .name("")
        .country("United Kingdom")
        .birthYear(1965)
        .build();

    Set<ConstraintViolation<Author>> violations = validator.validate(author);
    assertFalse(violations.isEmpty(), "Author with blank name should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Author name cannot be empty")));
  }

  @Test
  @DisplayName("Author with null name should fail validation")
  void testNullName() {
    Author author = Author.builder()
        .id(1)
        .name(null)
        .country("United Kingdom")
        .birthYear(1965)
        .build();

    Set<ConstraintViolation<Author>> violations = validator.validate(author);
    assertFalse(violations.isEmpty(), "Author with null name should fail");
  }

  @Test
  @DisplayName("Author with birth year less than 1000 should fail validation")
  void testInvalidBirthYear() {
    Author author = Author.builder()
        .id(1)
        .name("Ancient Author")
        .country("Greece")
        .birthYear(500)
        .build();

    Set<ConstraintViolation<Author>> violations = validator.validate(author);
    assertFalse(violations.isEmpty(), "Author with year < 1000 should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Birth year must be at least 1000")));
  }

  @Test
  @DisplayName("Author with null country should pass validation (optional field)")
  void testNullCountry() {
    Author author = Author.builder()
        .id(1)
        .name("Anonymous Author")
        .country(null)
        .birthYear(1950)
        .build();

    Set<ConstraintViolation<Author>> violations = validator.validate(author);
    assertTrue(violations.isEmpty(), "Author with null country should pass (optional)");
  }

  @Test
  @DisplayName("Author with null birth year should pass validation (optional field)")
  void testNullBirthYear() {
    Author author = Author.builder()
        .id(1)
        .name("Mystery Author")
        .country("Unknown")
        .birthYear(null)
        .build();

    Set<ConstraintViolation<Author>> violations = validator.validate(author);
    assertTrue(violations.isEmpty(), "Author with null birth year should pass (optional)");
  }

  @Test
  @DisplayName("Author with negative birth year should fail validation")
  void testNegativeBirthYear() {
    Author author = Author.builder()
        .id(1)
        .name("Future Author")
        .country("Tomorrow")
        .birthYear(-100)
        .build();

    Set<ConstraintViolation<Author>> violations = validator.validate(author);
    assertFalse(violations.isEmpty(), "Author with negative birth year should fail");
  }

  @Test
  @DisplayName("Multiple violations should be detected")
  void testMultipleViolations() {
    Author author = Author.builder()
        .id(1)
        .name("")
        .country("Country")
        .birthYear(800)
        .build();

    Set<ConstraintViolation<Author>> violations = validator.validate(author);
    assertEquals(2, violations.size(), "Should detect 2 violations");
  }
}
