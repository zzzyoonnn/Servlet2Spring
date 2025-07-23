package org.servlet2spring.todo.service;

import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;
import org.servlet2spring.todo.dto.ReplyDTO;

public interface ReplyService {

  Long register(ReplyDTO replyDTO);

  ReplyDTO read(Long rno);

  void modify(ReplyDTO replyDTO);

  void remove(Long rno);

  PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
