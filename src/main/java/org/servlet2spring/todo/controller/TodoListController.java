package org.servlet2spring.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoListController {

  private final TodoService todoService;

  @GetMapping("/list")
  public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
    log.info(pageRequestDTO);

    if (bindingResult.hasErrors()) {
      pageRequestDTO = PageRequestDTO.builder().build();
    }
    model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
  }

  @GetMapping("/register")
  public void registerGET() {
    log.info("GET todo register...");
  }
}
