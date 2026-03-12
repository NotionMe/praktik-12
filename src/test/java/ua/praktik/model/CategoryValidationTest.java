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

@DisplayName("Category Entity Validation Tests")
class CategoryValidationTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid Category should pass validation")
  void testValidCategory() {
    Category category = Category.builder()
        .id(1)
        .name("Fantasy")
        .description("Fantasy novels and stories")
        .build();

    Set<ConstraintViolation<Category>> violations = validator.validate(category);
    assertTrue(violations.isEmpty(), "Valid category should not have violations");
  }

  @Test
  @DisplayName("Category with blank name should fail validation")
  void testBlankName() {
    Category category = Category.builder()
        .id(1)
        .name("")
        .description("Some description")
        .build();

    Set<ConstraintViolation<Category>> violations = validator.validate(category);
    assertFalse(violations.isEmpty(), "Category with blank name should fail");
    assertTrue(violations.stream()
        .anyMatch(v -> v.getMessage().contains("Category name cannot be empty")));
  }

  @Test
  @DisplayName("Category with null name should fail validation")
  void testNullName() {
    Category category = Category.builder()
        .id(1)
        .name(null)
        .description("Some description")
        .build();

    Set<ConstraintViolation<Category>> violations = validator.validate(category);
    assertFalse(violations.isEmpty(), "Category with null name should fail");
  }

  @Test
  @DisplayName("Category with null description should pass validation (optional field)")
  void testNullDescription() {
    Category category = Category.builder()
        .id(1)
        .name("Mystery")
        .description(null)
        .build();

    Set<ConstraintViolation<Category>> violations = validator.validate(category);
    assertTrue(violations.isEmpty(), "Category with null description should pass (optional)");
  }

  @Test
  @DisplayName("Category with empty description should pass validation")
  void testEmptyDescription() {
    Category category = Category.builder()
        .id(1)
        .name("Science Fiction")
        .description("")
        .build();

    Set<ConstraintViolation<Category>> violations = validator.validate(category);
    assertTrue(violations.isEmpty(), "Category with empty description should pass");
  }

  @Test
  @DisplayName("Category with whitespace-only name should fail validation")
  void testWhitespaceOnlyName() {
    Category category = Category.builder()
        .id(1)
        .name("   ")
        .description("Description")
        .build();

    Set<ConstraintViolation<Category>> violations = validator.validate(category);
    assertFalse(violations.isEmpty(), "Category with whitespace-only name should fail");
  }

  @Test
  @DisplayName("Category with long name should pass validation")
  void testLongName() {
    String longName =
        "Contemporary Fiction and Drama with Historical Elements and Social Commentary";
    Category category = Category.builder()
        .id(1)
        .name(longName)
        .description("A very long and detailed description")
        .build();

    Set<ConstraintViolation<Category>> violations = validator.validate(category);
    assertTrue(violations.isEmpty(), "Category with long name should pass");
  }

  @Test
  @DisplayName("Category with special characters in name should pass validation")
  void testSpecialCharactersInName() {
    Category category = Category.builder()
        .id(1)
        .name("Sci-Fi & Fantasy")
        .description("Science Fiction and Fantasy novels")
        .build();

    Set<ConstraintViolation<Category>> violations = validator.validate(category);
    assertTrue(violations.isEmpty(), "Category with special characters should pass");
  }
}
