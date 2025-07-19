package org.servlet2spring.todo.repository;

import org.apache.ibatis.annotations.Param;
import org.servlet2spring.todo.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

  @Query("select r from Reply r where r.board.bno = :bno")
  Page<Reply> listOfBoard(@Param("bno") Long bno, Pageable pageable);
}
