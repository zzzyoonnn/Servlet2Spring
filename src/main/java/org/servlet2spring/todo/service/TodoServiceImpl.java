package org.servlet2spring.todo.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.servlet2spring.todo.domain.TodoVO;
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
  public List<TodoDTO> getAll() {
    List<TodoDTO> dtoList = todoMapper.selectAll().stream()
            .map(vo -> modelMapper.map(vo, TodoDTO.class))
            .collect(Collectors.toList());

    return dtoList;
  }
}
