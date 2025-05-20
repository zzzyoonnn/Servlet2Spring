package org.servlet2spring.service;

import java.time.LocalDate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.todo.service.TodoService;

@Log4j2
public class TodoServiceTests {

  private TodoService todoService;

  @BeforeEach
  public void ready() {
    todoService = TodoService.INSTANCE;
  }

  @Test
  public void testRegister() throws Exception {
    TodoDTO todoDTO = TodoDTO.builder()
            .title("JDBC Log4j2 Test(TodoService)")
            .dueDate(LocalDate.now())
            .build();

    log.info("----------테스트 코드의 Log4j2 설정 확인----------");
    log.info(todoDTO);

    todoService.register(todoDTO);
  }
}
