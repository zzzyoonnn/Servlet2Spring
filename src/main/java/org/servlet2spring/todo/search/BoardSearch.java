package org.servlet2spring.todo.search;

import java.awt.print.Pageable;
import org.servlet2spring.todo.domain.Board;
import org.springframework.data.domain.Page;

public interface BoardSearch {
  Page<Board> search1(Pageable pageable);
}
