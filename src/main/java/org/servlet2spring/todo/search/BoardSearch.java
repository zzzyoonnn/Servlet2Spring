package org.servlet2spring.todo.search;

import org.springframework.data.domain.Pageable;
import org.servlet2spring.todo.domain.Board;
import org.springframework.data.domain.Page;

public interface BoardSearch {
  Page<Board> search1(Pageable pageable);
}
