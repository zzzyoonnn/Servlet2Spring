package org.servlet2spring.repository;

import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class BoardRepositoryTests {

  @Autowired
  private BoardRepository boardRepository;

  @Test
  public void testInsert() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Board board = Board.builder()
              .title("titleNo : " + i)
              .content("contentNo : " + i)
              .writer("user" + (i % 10))
              .build();

      Board result = boardRepository.save(board);
      log.info("BNO: " + result.getBno());
    });
  }

}
