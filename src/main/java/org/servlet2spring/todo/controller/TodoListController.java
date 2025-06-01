package org.servlet2spring.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoListController {

  private final TodoService todoService;

//  @RequestMapping("/list")
//  public void list(Model model) {
//    log.info("todo list...");
//    model.addAttribute("dtoList", todoService.getAll());
//  }

  @GetMapping("/register")
  public void registerGET() {
    log.info("GET todo register...");
  }
}
