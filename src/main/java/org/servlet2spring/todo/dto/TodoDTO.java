package org.servlet2spring.todo.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

  private Long no;

  @NotEmpty
  private String title;

  @Future
  private LocalDate dueDate;

  private boolean finished;

  @NotEmpty
  private String writer;

}
