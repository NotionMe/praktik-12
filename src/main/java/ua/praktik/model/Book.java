package ua.praktik.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

  private Integer id;

  @NotBlank(message = "Title cannot be empty")
  private String title;

  @NotNull(message = "Author ID cannot be null")
  @Positive(message = "Author ID must be positive")
  private Integer authorId;

  @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "ISBN format is invalid")
  private String isbn;

  @Min(value = 1000, message = "Publication year must be at least 1000")
  private Integer publicationYear;

  @NotNull(message = "Publisher ID cannot be null")
  @Positive(message = "Publisher ID must be positive")
  private Integer publisherId;

  @Positive(message = "Category ID must be positive")
  private Integer categoryId;

  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
  private Double price;

  @Positive(message = "Page count must be positive")
  private Integer pageCount;

  private String imagePath;

  public StringProperty imagePathProperty() {
    return new SimpleStringProperty(this.imagePath);
  }
}
