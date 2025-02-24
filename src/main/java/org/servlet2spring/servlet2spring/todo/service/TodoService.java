package org.servlet2spring.servlet2spring.todo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.servlet2spring.servlet2spring.todo.dto.TodoDTO;

public enum TodoService {
  INSTANCE;

  private final List<TodoDTO> todoList = new ArrayList<>();

  // 새로운 TodoDTO 객체를 받아서 확인 가능
  public void register(TodoDTO todoDTO) {
    System.out.println("DEBUG... " + todoDTO);
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

  // 10개의 TodoDTO 객체를 만들어서 반환
  public List<TodoDTO> getList() {
    return IntStream.range(0, 10).mapToObj(i -> {
      TodoDTO dto = new TodoDTO();
      dto.setNo((long)i);
      dto.setTitle("Todo : " + i);
      dto.setDueDate(LocalDate.now());

      return dto;
    }).collect(Collectors.toList());
  }
}
