package org.servlet2spring.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.ReplyDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
@RequestMapping("/replies")
public class ReplyController {

  @ResponseBody
  @Operation(summary = "Replies POST", description = "POST 방식으로 댓글 등록")
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Long>> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException {

    log.info(replyDTO.toString());

    if (bindingResult.hasErrors()) {
      throw new BindException(bindingResult);
    }

    Map<String, Long> resultMap = Map.of("rno", 111L);
    return ResponseEntity.ok(resultMap);
  }

}
