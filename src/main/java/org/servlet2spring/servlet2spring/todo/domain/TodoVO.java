package org.servlet2spring.servlet2spring.todo.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor   // ModelMapper 이용으로 인한 추가
@NoArgsConstructor    // ModelMapper 이용으로 인한 추가
public class TodoVO {

  private Long no;

  private String title;

  private LocalDate dueDate;

  private boolean finished;

}
