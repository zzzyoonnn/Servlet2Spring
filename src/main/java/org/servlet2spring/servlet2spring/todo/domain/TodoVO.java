package org.servlet2spring.servlet2spring.todo.domain;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TodoVO {

  private Long no;

  private String title;

  private LocalDate dueDate;

  private boolean finished;

}
