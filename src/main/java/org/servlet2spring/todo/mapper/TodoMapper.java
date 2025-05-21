package org.servlet2spring.todo.mapper;

import org.servlet2spring.todo.domain.TodoVO;

public interface TodoMapper {

  String getTime();

  void insert(TodoVO todoVO);

}
