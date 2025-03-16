package org.servlet2spring.servlet2spring.todo.service;

import org.modelmapper.ModelMapper;
import org.servlet2spring.servlet2spring.todo.dao.TodoDAO;
import org.servlet2spring.servlet2spring.todo.domain.TodoVO;
import org.servlet2spring.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.servlet2spring.todo.util.MapperUtil;

public enum TodoService {
  INSTANCE;

  private TodoDAO todoDAO;
  private ModelMapper modelMapper;

  TodoService() {
    todoDAO = new TodoDAO();
    modelMapper = MapperUtil.INSTANCE.getModelMapper();
  }

  public void register(TodoDTO todoDTO) throws Exception {
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

    System.out.println("todoVO: " + todoVO);

    todoDAO.insert(todoVO);
  }

}
