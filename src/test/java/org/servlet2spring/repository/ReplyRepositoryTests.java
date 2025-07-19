package org.servlet2spring.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.domain.Reply;
import org.servlet2spring.todo.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

  @Test
  public void testBoardReplies() {
    Long bno = 100L;

    Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
    Page<Reply> result = replyRepository.listOfBoard(bno, pageable);

    result.getContent().forEach(reply -> {
      log.info(reply);
    });
  }
}
