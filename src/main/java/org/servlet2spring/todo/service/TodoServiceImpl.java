package org.servlet2spring.todo.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.servlet2spring.todo.domain.TodoVO;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;
import org.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.todo.mapper.TodoMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

  private final TodoMapper todoMapper;
  private final ModelMapper modelMapper;

  // 등록 기능
  @Override
  public void register(TodoDTO todoDTO) {
    log.info(modelMapper);

    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
    log.info(todoVO);
    todoMapper.insert(todoVO);
  }

  // 목록 기능
  @Override
  public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
    List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
    List<TodoDTO> dtoList = voList.stream()
            .map(vo -> modelMapper.map(vo, TodoDTO.class))
            .collect(Collectors.toList());

    int total = todoMapper.getCount(pageRequestDTO);

    PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
            .dtoList(dtoList)
            .total(total)
            .pageRequestDTO(pageRequestDTO)
            .build();

    return pageResponseDTO;
  }

  // 조회 기능
  @Override
  public TodoDTO getOne(long no) {
    TodoVO todoVO = todoMapper.selectOne(no);
    TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

    return todoDTO;
  }

  // 삭제 기능
  @Override
  public void remove(long no) {
    todoMapper.delete(no);
  }

  // 수정 기능
  @Override
  public void modify(TodoDTO todoDTO) {
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
    todoMapper.update(todoVO);
  }
}
