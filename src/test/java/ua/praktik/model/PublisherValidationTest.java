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

@DisplayName("Publisher Entity Validation Tests")
class PublisherValidationTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid Publisher should pass validation")
  void testValidPublisher() {
    Publisher publisher = Publisher.builder()
        .id(1)
        .name("Bloomsbury Publishing")
        .country("United Kingdom")
        .foundedYear(1986)
        .build();

    Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
    assertTrue(violations.isEmpty(), "Valid publisher should not have violations");
  }

  @Test
  @DisplayName("Publisher with blank name should fail validation")
  void testBlankName() {
    Publisher publisher = Publisher.builder()
        .id(1)
        .name("")
        .country("United Kingdom")
        .foundedYear(1986)
        .build();

    Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
    assertFalse(violations.isEmpty(), "Publisher with blank name should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Publisher name cannot be empty")));
  }

  @Test
  @DisplayName("Publisher with null name should fail validation")
  void testNullName() {
    Publisher publisher = Publisher.builder()
        .id(1)
        .name(null)
        .country("United Kingdom")
        .foundedYear(1986)
        .build();

    Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
    assertFalse(violations.isEmpty(), "Publisher with null name should fail");
  }

  @Test
  @DisplayName("Publisher with founded year less than 1000 should fail validation")
  void testInvalidFoundedYear() {
    Publisher publisher = Publisher.builder()
        .id(1)
        .name("Ancient Publisher")
        .country("Rome")
        .foundedYear(500)
        .build();

    Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
    assertFalse(violations.isEmpty(), "Publisher with year < 1000 should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Founded year must be at least 1000")));
  }

  @Test
  @DisplayName("Publisher with null country should pass validation (optional field)")
  void testNullCountry() {
    Publisher publisher = Publisher.builder()
        .id(1)
        .name("Global Publisher")
        .country(null)
        .foundedYear(2000)
        .build();

    Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
    assertTrue(violations.isEmpty(), "Publisher with null country should pass (optional)");
  }

  @Test
  @DisplayName("Publisher with null founded year should pass validation (optional field)")
  void testNullFoundedYear() {
    Publisher publisher = Publisher.builder()
        .id(1)
        .name("Mystery Publisher")
        .country("Country")
        .foundedYear(null)
        .build();

    Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
    assertTrue(violations.isEmpty(), "Publisher with null founded year should pass (optional)");
  }

  @Test
  @DisplayName("Publisher with negative founded year should fail validation")
  void testNegativeFoundedYear() {
    Publisher publisher = Publisher.builder()
        .id(1)
        .name("Future Publisher")
        .country("Tomorrow")
        .foundedYear(-100)
        .build();

    Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
    assertFalse(violations.isEmpty(), "Publisher with negative founded year should fail");
  }

  @Test
  @DisplayName("Publisher with whitespace-only name should fail validation")
  void testWhitespaceOnlyName() {
    Publisher publisher = Publisher.builder()
        .id(1)
        .name("   ")
        .country("Country")
        .foundedYear(1995)
        .build();

    Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
    assertFalse(violations.isEmpty(), "Publisher with whitespace-only name should fail");
  }
}
