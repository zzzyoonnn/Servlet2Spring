package org.servlet2spring.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;
import org.servlet2spring.todo.dto.ReplyDTO;
import org.servlet2spring.todo.service.ReplyService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

  private final ReplyService replyService;

  // 댓글 등록
  @ResponseBody
  @Operation(summary = "Replies POST", description = "POST 방식으로 댓글 등록")
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException {
    log.info(replyDTO);

    if (bindingResult.hasErrors()) {
      throw new BindException(bindingResult);
    }

    Map<String, Long> resultMap = new HashMap<>();
    Long rno = replyService.register(replyDTO);
    resultMap.put("rno", rno);

    return resultMap;
  }

  // 특정 댓글 조회
  @Operation(summary = "Read Reply", description = "GET 방식으로 특정 댓글 조회")
  @GetMapping("/{rno}")
  @ResponseBody
  public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno) {
    ReplyDTO replyDTO = replyService.read(rno);

    return replyDTO;
  }

  // 특정 댓글 수정
  @ResponseBody
  @Operation(summary = "Modify Reply", description = "PUT 방식으로 특정 댓글 수정")
  @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Long> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO) {
    replyDTO.setRno(rno);
    replyService.modify(replyDTO);
    Map<String, Long> resultMap = new HashMap<>();
    resultMap.put("rno", rno);

    return resultMap;
  }

  // 특정 댓글 삭제
  @ResponseBody
  @Operation(summary = "Delete Reply", description = "DELETE 방식으로 특정 댓글 삭제")
  @DeleteMapping("/{rno}")
  public Map<String, Long> remove(@PathVariable("rno") Long rno) {
    replyService.remove(rno);
    Map<String, Long> resultMap = new HashMap<>();
    resultMap.put("rno", rno);

    return resultMap;
  }

  // 특정 게시물의 댓글 목록
  @Operation(summary = "Replies of Board", description = "GET 방식으로 특정 게시물의 댓글 목록")
  @GetMapping("/list/{bno}")
  @ResponseBody
  public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO) {
    PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);

    return responseDTO;
  }
}
