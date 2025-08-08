package org.servlet2spring.todo.repository;

import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.apache.ibatis.annotations.Param;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

  //Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);
  @Query("select b from Board b where b.title like concat('%', :keyword, '%')")
  Page<Board> findKeyword(@Param("keyword") String keyword, Pageable pageable);

  @Query(value = "select now()", nativeQuery = true)
  String getTime();

  @EntityGraph(attributePaths = {"imageSet"})
  @Query("select b from Board b where b.bno =:bno")
  Optional<Board> findByIdWithImages(Long bno);
}
