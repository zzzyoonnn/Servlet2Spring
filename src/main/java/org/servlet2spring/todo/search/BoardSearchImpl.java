package org.servlet2spring.todo.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import org.hibernate.annotations.DialectOverride.SQLRestriction;
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
    QBoard board = QBoard.board;

    // 기본 쿼리
    JPQLQuery<Board> query = from(board);
    query.where(board.title.contains("1"));

    // paging
    this.getQuerydsl().applyPagination(pageable, query);

    List<Board> list = query.fetch();

    long count = query.fetchCount();

    return null;
  }

  @Override
  public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    JPQLQuery<Board> query = from(board);

    if ((types != null && types.length > 0) && keyword != null) {
      // 검색 조건과 키워드가 있다면
      BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
      for (String type : types) {
        switch (type) {
          case "t":
            booleanBuilder.or(board.title.contains(keyword));
            break;
          case "c":
            booleanBuilder.or(board.content.contains(keyword));
            break;
          case "w":
            booleanBuilder.or(board.writer.contains(keyword));
            break;
        }
      } // end
      query.where(booleanBuilder);
    } // end if

    // bno > 0
    query.where(board.bno.gt(0L));

    // paging
    this.getQuerydsl().applyPagination(pageable, query);

    List<Board> list = query.fetch();

    long count = query.fetchCount();

    return null;
  }
}
