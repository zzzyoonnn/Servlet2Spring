package org.servlet2spring.servlet2spring.todo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.servlet2spring.servlet2spring.todo.dto.TodoDTO;

public enum TodoService {
  INSTANCE;

  public void register(TodoDTO todoDTO) {
    System.out.println("DEBUG... " + todoDTO);
  }

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
