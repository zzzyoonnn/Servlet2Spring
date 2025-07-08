package org.servlet2spring.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class BoardServiceTests {

  @Autowired
  private BoardService boardService;

  @Test
  public void testRegister() {
    log.info(boardService.getClass().getName());
  }
}
