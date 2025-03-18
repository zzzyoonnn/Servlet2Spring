package org.servlet2spring.servlet2spring.todo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

  // 특정한 번호 조회 기능
  public TodoDTO get(Long no) {
    TodoDTO todoDto = new TodoDTO();
    todoDto.setNo(no);
    todoDto.setTitle("Sample Todo");
    todoDto.setDueDate(LocalDate.now());
    todoDto.setFinished(true);

    return todoDto;
  }

  public void register(TodoDTO todoDTO) throws Exception {
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

    System.out.println("todoVO: " + todoVO);

    todoDAO.insert(todoVO);
  }

  // 10개의 TodoDTO 객체를 만들어서 반환
  public List<TodoDTO> getList() {
    return IntStream.range(0, 10).mapToObj(i -> {
      TodoDTO dto = new TodoDTO();
      dto.setNo((long) i);
      dto.setTitle("Todo : " + i);
      dto.setDueDate(LocalDate.now());

      return dto;
    }).collect(Collectors.toList());
  }

}
