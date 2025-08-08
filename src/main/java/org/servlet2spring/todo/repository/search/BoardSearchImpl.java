package org.servlet2spring.todo.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.domain.QBoard;
import org.servlet2spring.todo.domain.QReply;
import org.servlet2spring.todo.dto.BoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    return new PageImpl<>(list, pageable, count);
  }

  @Override
  public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    QReply reply = QReply.reply;

    JPQLQuery<Board> query = from(board);
    query.leftJoin(reply).on(reply.board.eq(board));

    query.groupBy(board);

    if ((types != null && types.length > 0) && keyword != null) {
      BooleanBuilder booleanBuilder = new BooleanBuilder();

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
      } // end for
      query.where(booleanBuilder);
    }

    // bno > 0
    query.where(board.bno.gt(0L));

    JPQLQuery<BoardListReplyCountDTO> dtoQuery = query.select(Projections.bean(BoardListReplyCountDTO.class,
            board.bno,
            board.title,
            board.writer,
            board.regdate.as("regDate"),
            reply.count().as("replyCount")
    ));

    this.getQuerydsl().applyPagination(pageable, dtoQuery);
    List<BoardListReplyCountDTO> dtoList = dtoQuery.fetch();
    long count = dtoQuery.fetchCount();

    return new PageImpl<>(dtoList, pageable, count);
  }

  @Override
  public Page<BoardListReplyCountDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {

    QBoard board = QBoard.board;
    QReply reply = QReply.reply;

    JPQLQuery<Board> query = from(board);
    query.leftJoin(reply).on(reply.board.eq(board));

    getQuerydsl().applyPagination(pageable, query);

    List<Board> list = query.fetch();

    list.forEach(board1 -> {
      System.out.println(board1.getBno());
      System.out.println(board1.getImageSet());
      System.out.println("-----------------------");
    });

    return null;
  }
}
