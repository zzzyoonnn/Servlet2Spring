package org.servlet2spring.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

  @Test
  public void testSelect() {
    Long bno = 100L;
    Optional<Board> result = boardRepository.findById(bno);
    Board board = result.orElseThrow();
    log.info(board);
  }

  @Test
  public void testUpdate() {
    Long bno = 100L;
    Optional<Board> result = boardRepository.findById(bno);
    Board board = result.orElseThrow();
    board.change("update title", "update content");
    boardRepository.save(board);
  }

  @Test
  public void testDelete() {
    Long bno = 99L;
    boardRepository.deleteById(bno);
  }

  @Test
  public void testPaging() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

    Page<Board> result = boardRepository.findAll(pageable);
    log.info("total count: " + result.getTotalElements());
    log.info("total pages: " + result.getTotalPages());
    log.info("page number: " + result.getNumber());
    log.info("page size: " + result.getSize());

    List<Board> todoList = result.getContent();
    todoList.forEach(board -> log.info(board));
  }

}
