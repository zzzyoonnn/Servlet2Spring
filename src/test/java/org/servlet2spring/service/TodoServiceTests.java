package org.servlet2spring.service;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.servlet2spring.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.servlet2spring.todo.service.TodoService;

public class TodoServiceTests {

  private TodoService todoService;

  @BeforeEach
  public void ready() {
    todoService = TodoService.INSTANCE;
  }

  @Test
  public void testRegister() throws Exception {
    TodoDTO todoDTO = TodoDTO.builder()
            .title("JDBC Test(TodoService)")
            .dueDate(LocalDate.now())
            .build();

    todoService.register(todoDTO);
  }
}
