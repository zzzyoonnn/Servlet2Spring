package org.servlet2spring.servlet2spring.todo.domain;

import lombok.Getter;
import lombok.Builder;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class TodoVO {

  private Long no;

  private String title;

  private LocalDateTime dueDate;

  private boolean finished;

}
