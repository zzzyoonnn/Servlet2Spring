package org.servlet2spring.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.domain.Reply;
import org.servlet2spring.todo.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class ReplyRepositoryTests {

  @Autowired
  private ReplyRepository replyRepository;

  @Test
  public void testInsert() {
    // 실제 DB에 있는 bno
    Long bno = 100L;

    Board board = Board.builder().bno(bno).build();

    Reply reply = Reply.builder()
            .board(board)
            .replyText("reply repository test")
            .replyer("replier1")
            .build();

    replyRepository.save(reply);
  }
}
