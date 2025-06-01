package org.servlet2spring.todo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

  @Builder.Default
  @Min(value = 1)
  @Positive
  private int page = 1;

  @Builder.Default
  @Min(value = 10)
  @Max(value = 100)
  @Positive
  private int size = 10;

  public int getSkip() {
    return (page - 1) * size;
  }
}
