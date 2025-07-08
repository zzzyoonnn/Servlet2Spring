package org.servlet2spring.todo.service;

import org.servlet2spring.todo.dto.BoardDTO;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;

public interface BoardService {
  Long register(BoardDTO boardDTO);

  BoardDTO readOne(Long bno);

  void modify(BoardDTO boardDTO);

  void remove(Long bno);

  PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
