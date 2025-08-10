package org.servlet2spring.todo.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.servlet2spring.todo.domain.Board;
import org.servlet2spring.todo.dto.BoardDTO;
import org.servlet2spring.todo.dto.BoardListAllDTO;
import org.servlet2spring.todo.dto.BoardListReplyCountDTO;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;
import org.servlet2spring.todo.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Board board = dtoToEntity(boardDTO);
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

  // 삭제
  @Override
  public void remove(Long bno) {
    boardRepository.deleteById(bno);
  }

  // 페이징 처리
  @Override
  public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
    String[] types = pageRequestDTO.getTypes();
    String keyword = pageRequestDTO.getKeyword();
    Pageable pageable = pageRequestDTO.getPageable("bno");

    Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

    List<BoardDTO> dtoList = result.getContent().stream()
            .map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

    return PageResponseDTO.<BoardDTO>withAll()
            .pageRequestDTO(pageRequestDTO)
            .dtoList(dtoList)
            .total((int)result.getTotalElements())
            .build();
  }

  @Override
  public PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO) {
    String[] types = pageRequestDTO.getTypes();
    String keyword = pageRequestDTO.getKeyword();
    Pageable pageable = pageRequestDTO.getPageable("bno");

    Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

    return PageResponseDTO.<BoardListReplyCountDTO>withAll()
            .pageRequestDTO(pageRequestDTO)
            .dtoList(result.getContent())
            .total((int)result.getTotalElements())
            .build();
  }

  @Override
  public PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {
    return null;
  }
}
