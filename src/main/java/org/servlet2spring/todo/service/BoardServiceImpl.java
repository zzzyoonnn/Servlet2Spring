package org.servlet2spring.todo.service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.dto.BoardDTO;
import org.servlet2spring.todo.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
  private final ModelMapper modelMapper;
  private final BoardRepository boardRepository;

  // 등록
  @Override
  public Long register(BoardDTO boardDTO) {
    Board board = modelMapper.map(boardDTO, Board.class);
    Long bno = boardRepository.save(board).getBno();
    return bno;
  }

  // 조회
  @Override
  public BoardDTO readOne(Long bno) {
    Optional<Board> result = boardRepository.findById(bno);
    Board board = result.orElseThrow();
    BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
    return boardDTO;
  }

  // 수정
  @Override
  public void modify(BoardDTO boardDTO) {
    Optional<Board> result = boardRepository.findById(boardDTO.getBno());
    Board board = result.orElseThrow();
    board.change(boardDTO.getTitle(), boardDTO.getContent());
    boardRepository.save(board);
  }
}
