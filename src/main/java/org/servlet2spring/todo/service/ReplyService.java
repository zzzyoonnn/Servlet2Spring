package org.servlet2spring.todo.service;

import org.servlet2spring.todo.dto.ReplyDTO;

public interface ReplyService {
  Long register(ReplyDTO replyDTO);
}
