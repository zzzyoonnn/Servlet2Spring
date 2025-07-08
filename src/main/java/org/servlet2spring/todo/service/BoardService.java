package org.servlet2spring.todo.service;

import org.servlet2spring.todo.dto.BoardDTO;

public interface BoardService {
  Long register(BoardDTO boardDTO);

  BoardDTO readOne(Long bno);

  void modify(BoardDTO boardDTO);
}
