package org.servlet2spring.service;

import java.time.LocalDate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;
import org.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

  @Autowired
  private TodoService todoService;

  @Test
  public void testRegister() {
    TodoDTO todoDTO = TodoDTO.builder()
            .title("Register Test")
            .dueDate(LocalDate.now())
            .writer("user01")
            .build();

    todoService.register(todoDTO);
  }

  @Test
  public void testPaging() {
    PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
    PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);
    log.info(responseDTO);
    responseDTO.getDtoList().stream().forEach(todoDTO -> log.info(todoDTO));
  }
}
