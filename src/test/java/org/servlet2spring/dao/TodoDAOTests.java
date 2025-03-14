package org.servlet2spring.dao;

import java.time.LocalDate;
import java.util.List;
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
  public void testDeleteOne() throws Exception {
    Long no = 9L;

    todoDAO.deleteOne(no);
  }

  @Test
  public void testUpdateOne() throws Exception {
    TodoVO todoVO = TodoVO.builder()
            .no(8L)
            .title("test title(edited)")
            .dueDate(LocalDate.of(1999,12,31))
            .finished(true)
            .build();

    todoDAO.updateOne(todoVO);
  }

  @Test
  public void testSelectOne() throws Exception {
    Long no = 1L;   // 반드시 존재하는 번호 사용

    TodoVO vo = todoDAO.selectOne(no);

    System.out.println(vo);
  }

  @Test
  public void testSelectAll() throws Exception {
    List<TodoVO> list = todoDAO.selectAll();

    list.forEach(vo -> System.out.println(vo));
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
