package org.servlet2spring.service;

import java.util.Arrays;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.dto.BoardDTO;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;
import org.servlet2spring.todo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class BoardServiceTests {

  @Autowired
  private BoardService boardService;

  @Test
  public void testRegister() {
    log.info(boardService.getClass().getName());

    BoardDTO boardDTO = BoardDTO.builder()
            .title("test register")
            .content("test content")
            .writer("user00")
            .build();

    Long bno = boardService.register(boardDTO);

    log.info("bno: " + bno);
  }

  @Test
  public void testModify() {
    // 변경에 필요한 데이터만 작성
    BoardDTO boardDTO = BoardDTO.builder()
            .bno(101L)
            .title("test modify")
            .content("test test")
            .build();

    boardService.modify(boardDTO);
  }

  @Test
  public void testList() {
    PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
            .type("tcw")
            .keyword("1")
            .page(1)
            .size(10)
            .build();

    PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

    log.info(responseDTO);
  }

  @Test
  public void testRegisterWithImages() {
    log.info(boardService.getClass().getName());

    BoardDTO boardDTO = BoardDTO.builder()
            .title("Test board with images")
            .content("Sample content")
            .writer("user00")
            .build();

    boardDTO.setFileNames(
            Arrays.asList(
                    UUID.randomUUID()+"_aaa.jpg",
                    UUID.randomUUID()+"_bbb.jpg",
                    UUID.randomUUID()+"_ccc.jpg"
            )
    );

    Long bno = boardService.register(boardDTO);
    log.info("bno: " + bno);
  }

  @Test
  public void testReadAll() {
    Long bno = 101L;
    BoardDTO boardDTO = boardService.readOne(bno);
    log.info(boardDTO);

    for (String fileName : boardDTO.getFileNames()) {
      log.info(fileName);
    }
  }
}
