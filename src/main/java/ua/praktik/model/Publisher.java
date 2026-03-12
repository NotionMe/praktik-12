package ua.praktik.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {

  private Integer id;

  @NotBlank(message = "Publisher name cannot be empty")
  private String name;

  private String country;

  @Min(value = 1000, message = "Founded year must be at least 1000")
  private Integer foundedYear;
}
