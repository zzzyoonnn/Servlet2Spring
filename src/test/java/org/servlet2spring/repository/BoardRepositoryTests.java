package org.servlet2spring.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.domain.BoardImage;
import org.servlet2spring.todo.dto.BoardListAllDTO;
import org.servlet2spring.todo.dto.BoardListReplyCountDTO;
import org.servlet2spring.todo.repository.BoardRepository;
import org.servlet2spring.todo.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

@Log4j2
@SpringBootTest
public class BoardRepositoryTests {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private ReplyRepository replyRepository;

  @Test
  public void testInsert() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Board board = Board.builder()
              .title("titleNo : " + i)
              .content("contentNo : " + i)
              .writer("user" + (i % 10))
              .build();

      Board result = boardRepository.save(board);
      log.info("BNO: " + result.getBno());
    });
  }

  @Test
  public void testSelect() {
    Long bno = 100L;
    Optional<Board> result = boardRepository.findById(bno);
    Board board = result.orElseThrow();
    log.info(board);
  }

  @Test
  public void testUpdate() {
    Long bno = 100L;
    Optional<Board> result = boardRepository.findById(bno);
    Board board = result.orElseThrow();
    board.change("update title", "update content");
    boardRepository.save(board);
  }

  @Test
  public void testDelete() {
    Long bno = 99L;
    boardRepository.deleteById(bno);
  }

  @Test
  public void testPaging() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

    Page<Board> result = boardRepository.findAll(pageable);
    log.info("total count: " + result.getTotalElements());
    log.info("total pages: " + result.getTotalPages());
    log.info("page number: " + result.getNumber());
    log.info("page size: " + result.getSize());

    List<Board> todoList = result.getContent();
    todoList.forEach(board -> log.info(board));
  }

  @Test
  public void testSearch1() {
    //2 page order by bno desc
    Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());

    boardRepository.search1(pageable);
  }

  @Test
  public void testSearchAll() {
    String[] types = {"t", "c", "w"};

    String keyword = "1";

    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
    Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

    // total pages
    log.info(result.getTotalPages());

    // page size
    log.info(result.getSize());

    // page number
    log.info(result.getNumber());

    // prev next
    log.info(result.hasPrevious() + ": " + result.hasNext());

    result.getContent().forEach(board -> log.info(board));
  }

  @Test
  public void testSearchReplyCount() {
    String[] types = {"t", "c", "w"};

    String keyword = "1";

    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
    Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

    // total pages
    log.info(result.getTotalPages());

    // page size
    log.info(result.getSize());

    // page number
    log.info(result.getNumber());

    // prev next
    log.info(result.hasPrevious() + ": " + result.hasNext());

    result.getContent().forEach(board -> log.info(board));
  }

  @Test
  public void testInsertWithImages() {
    Board board = Board.builder()
            .title("Image test")
            .content("첨부파일 테스트")
            .writer("tester")
            .build();

    for (int i = 0; i < 3; i++) {
      board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
    }

    boardRepository.save(board);
  }

  // board 조회 시, 이미지 조회 기능 테스트
  @Test
  public void testReadWithImages() {

    // 반드시 존재하는 bno로 확인
    Optional<Board> result = boardRepository.findByIdWithImages(1L);

    Board board = result.orElseThrow();

    log.info(board);
    log.info("------------------------------");
//    log.info(board.getImageSet());
    for (BoardImage boardImage : board.getImageSet()) {
      log.info(boardImage);
    }
  }

  // 특정 게시물의 첨부파일 수정
  @Transactional
  @Commit
  @Test
  public void testModifyImages() {
    Optional<Board> result = boardRepository.findByIdWithImages(1L);
    Board board = result.orElseThrow();

    // 기존의 첨부파일 삭제
    board.clearImages();

    // 새로운 첨부파일
    for (int i = 0; i < 2; i++) {
      board.addImage(UUID.randomUUID().toString(), "updatefile" + i + ".jpg");
    }

    boardRepository.save(board);
  }

  @Test
  @Transactional
  @Commit
  public void testRemoveAll() {
    Long bno = 1L;

    replyRepository.deleteByBoard_Bno(bno);

    boardRepository.deleteById(bno);
  }

  @Test
  @Transactional
  public void testSearchImageReplyCount() {

    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

    //boardRepository.searchWithAll(null, null, pageable);

    Page<BoardListAllDTO> result = boardRepository.searchWithAll(null, null, pageable);

    log.info("---------------------");
    log.info(result.getTotalElements());

    result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
  }
}
