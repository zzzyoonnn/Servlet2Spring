package org.servlet2spring.todo.service;

import java.util.List;
import org.servlet2spring.todo.dto.TodoDTO;

public interface TodoService {

  void register(TodoDTO todoDTO);

  List<TodoDTO> getAll();

  TodoDTO getOne(long no);

  void remove(long no);
}
