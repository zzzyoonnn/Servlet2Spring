package org.servlet2spring.todo.controller;

import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoRemoveController extends HttpServlet {

  private final TodoService todoService;

  @PostMapping("/remove")
  public String remove(@RequestParam("no") Long no, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO) {
    log.info("-----remove-----");
    log.info("no: " + no);

    todoService.remove(no);

    return "redirect:/todo/list?" + pageRequestDTO.getLink();
  }
}