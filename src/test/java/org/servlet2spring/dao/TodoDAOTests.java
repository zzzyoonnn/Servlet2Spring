package org.servlet2spring.dao;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.servlet2spring.servlet2spring.todo.dao.TodoDAO;
import org.servlet2spring.servlet2spring.todo.domain.TodoVO;

public class TodoDAOTests {

  private TodoDAO todoDAO;

  @BeforeEach
  public void ready() {
    todoDAO = new TodoDAO();
  }

  @Test
  public void testInsert() throws Exception {
    TodoVO todoVO = TodoVO.builder()
            .title("test title")
            .dueDate(LocalDate.of(2025,03,14))
            .build();

    todoDAO.insert(todoVO);
  }

  @Test
  public void testTime() throws Exception {
    System.out.println(todoDAO.getTime());
  }
}
