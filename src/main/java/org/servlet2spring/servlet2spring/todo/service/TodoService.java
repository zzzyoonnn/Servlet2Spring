package org.servlet2spring.servlet2spring.todo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.servlet2spring.servlet2spring.todo.dao.TodoDAO;
import org.servlet2spring.servlet2spring.todo.domain.TodoVO;
import org.servlet2spring.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.servlet2spring.todo.util.MapperUtil;

@Log4j2
public enum TodoService {
  INSTANCE;

  private TodoDAO todoDAO;
  private ModelMapper modelMapper;

  TodoService() {
    todoDAO = new TodoDAO();
    modelMapper = MapperUtil.INSTANCE.getModelMapper();
  }

  // 수정 기능 구현
  public void modify(TodoDTO todoDTO) throws Exception {
    log.info("todoDTO: " + todoDTO);

    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
    todoDAO.updateOne(todoVO);
  }

  // 조회 기능 구현
  public TodoDTO get(Long no) throws Exception {
    log.info("no: " + no);
    TodoVO todoVO = todoDAO.selectOne(no);
    TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
    return todoDTO;
  }

  // 등록 기능 구현
  public void register(TodoDTO todoDTO) throws Exception {
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

    log.info(todoVO);
    todoDAO.insert(todoVO);
  }

  // 목록 기능 구현
  public List<TodoDTO> listAll() throws Exception {
    List<TodoVO> voList = todoDAO.selectAll();

    log.info("voList...");

    log.info(voList);

    List<TodoDTO> dtoList = voList.stream()
            .map(vo -> modelMapper.map(vo, TodoDTO.class))
            .collect(Collectors.toList());

    return dtoList;
  }
}
