package org.servlet2spring.todo.search;

import org.servlet2spring.todo.dto.BoardListReplyCountDTO;
import org.springframework.data.domain.Pageable;
import org.servlet2spring.todo.domain.Board;
import org.springframework.data.domain.Page;

public interface BoardSearch {
  Page<Board> search1(Pageable pageable);

  Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

  Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
