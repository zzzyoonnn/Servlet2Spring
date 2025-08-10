package org.servlet2spring.todo.service;

import java.util.List;
import java.util.stream.Collectors;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.dto.BoardDTO;
import org.servlet2spring.todo.dto.BoardListAllDTO;
import org.servlet2spring.todo.dto.BoardListReplyCountDTO;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;

public interface BoardService {
  Long register(BoardDTO boardDTO);

  BoardDTO readOne(Long bno);

  void modify(BoardDTO boardDTO);

  void remove(Long bno);

  PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

  PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

  // 게시글의 이미지와 댓글 수까지 처리
  PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

  // 등록
  default Board dtoToEntity(BoardDTO boardDTO) {
    Board board = Board.builder()
            .bno(boardDTO.getBno())
            .title(boardDTO.getTitle())
            .content(boardDTO.getContent())
            .writer(boardDTO.getWriter())
            .build();

    if (boardDTO.getFileNames() != null) {
      boardDTO.getFileNames().forEach(fileName -> {
        String[] arr = fileName.split("_");
        board.addImage(arr[0], arr[1]);
      });
    }

    return board;
  }

  // 조회
  default BoardDTO entityToDTO(Board board) {
    BoardDTO boardDTO = BoardDTO.builder()
            .bno(board.getBno())
            .title(board.getTitle())
            .content(board.getContent())
            .writer(board.getWriter())
            .regDate(board.getRegdate())
            .modDate(board.getModdate())
            .build();

    List<String> fileNames = board.getImageSet().stream().sorted()
            .map(boardImage -> boardImage.getUuid() + "_" + boardImage.getFileName()).collect(
                    Collectors.toList());

    boardDTO.setFileNames(fileNames);

    return boardDTO;
  }
}
