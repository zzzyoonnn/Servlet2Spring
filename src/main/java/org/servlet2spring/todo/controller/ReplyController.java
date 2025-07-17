package org.servlet2spring.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.ReplyDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/replies")
public class ReplyController {

  @Operation(summary = "Replies POST", description = "POST 방식으로 댓글 등록")
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Long>> register(@RequestBody ReplyDTO replyDTO) {

    log.info(replyDTO.toString());
    Map<String, Long> resultMap = Map.of("rno", 111L);
    return ResponseEntity.ok(resultMap);
  }

}
