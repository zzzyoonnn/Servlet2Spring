package org.servlet2spring.todo.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoModifyController extends HttpServlet {

  private final TodoService todoService;

  @PostMapping("/modify")
  public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO) {
    if (bindingResult.hasErrors()) {
      log.info("has errors: {}", bindingResult.getAllErrors());

      redirectAttributes.addAttribute("no", todoDTO.getNo());
      return "redirect:/todo/modify";
    }

    log.info(todoDTO);
    todoService.modify(todoDTO);

    redirectAttributes.addAttribute("no", pageRequestDTO.getTo());

    return "redirect:/todo/read";
  }
}
