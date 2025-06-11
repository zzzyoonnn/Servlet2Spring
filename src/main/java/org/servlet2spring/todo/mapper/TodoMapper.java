package org.servlet2spring.todo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.servlet2spring.todo.domain.TodoVO;
import org.servlet2spring.todo.dto.PageRequestDTO;

@Mapper
public interface TodoMapper {

  String getTime();

  void insert(TodoVO todoVO);

  List<TodoVO> selectAll();

  TodoVO selectOne(long no);

  void delete(long no);

  void update(TodoVO todoVO);

  List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

  int getCount(PageRequestDTO pageRequestDTO);
}
