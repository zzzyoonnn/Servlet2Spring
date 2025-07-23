package org.servlet2spring.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.dto.ReplyDTO;
import org.servlet2spring.todo.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class ReplyServiceTests {

  @Autowired
  private ReplyService replyService;

  @Test
  public void testRegister() {
    ReplyDTO replyDTO = ReplyDTO.builder()
            .replyText("ReplyDTO test")
            .replier("replier")
            .bno(307L)
            .build();

    log.info(replyService.register(replyDTO));
  }
}
