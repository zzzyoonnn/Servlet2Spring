package org.servlet2spring.todo.search;

import com.querydsl.jpa.JPQLQuery;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.domain.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

  public BoardSearchImpl() {
    super(Board.class);
  }

  @Override
  public Page<Board> search1(Pageable pageable) {
    return null;
  }
}
