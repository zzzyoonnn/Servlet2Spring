package org.servlet2spring.todo.service;

import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;
import org.servlet2spring.todo.dto.TodoDTO;

public interface TodoService {

  void register(TodoDTO todoDTO);

  PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

  TodoDTO getOne(long no);

  void remove(long no);

  void modify(TodoDTO todoDTO);
}
