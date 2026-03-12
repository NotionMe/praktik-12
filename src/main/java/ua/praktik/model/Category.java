package ua.praktik.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

  private Integer id;

  @NotBlank(message = "Category name cannot be empty")
  private String name;

  private String description;
}
