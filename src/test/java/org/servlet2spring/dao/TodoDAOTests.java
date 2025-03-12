package org.servlet2spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.servlet2spring.servlet2spring.todo.dao.TodoDAO;

public class TodoDAOTests {

  private TodoDAO todoDAO;

  @BeforeEach
  public void ready() {
    todoDAO = new TodoDAO();
  }

  @Test
  public void testTime() throws Exception {
    System.out.println(todoDAO.getTime());
  }
}
