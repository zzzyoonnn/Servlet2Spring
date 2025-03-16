package org.servlet2spring.servlet2spring.todo.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

  private Long no;

  private String title;

  private LocalDate dueDate;

  private boolean finished;

}
