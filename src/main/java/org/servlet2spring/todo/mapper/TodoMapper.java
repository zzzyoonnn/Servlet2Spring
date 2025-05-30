package org.servlet2spring.todo.mapper;

import java.util.List;
import org.servlet2spring.todo.domain.TodoVO;

public interface TodoMapper {

  String getTime();

  void insert(TodoVO todoVO);

  List<TodoVO> selectAll();

  TodoVO selectOne(long no);

  void delete(long no);

  void update(TodoVO todoVO);
}
