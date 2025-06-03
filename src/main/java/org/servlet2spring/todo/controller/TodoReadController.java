package org.servlet2spring.todo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoReadController extends HttpServlet {

  private final TodoService todoService;

  @GetMapping({"/read", "/modify"})
  public void read(@RequestParam("no") Long no, Model model, PageRequestDTO pageRequestDTO) {
    TodoDTO todoDTO = todoService.getOne(no);
    log.info(todoDTO);

    model.addAttribute("dto", todoDTO);
  }

  private Cookie findCookie(Cookie[] cookies, String cookieName) {

    Cookie targetCookie = null;

    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(cookieName)) {
          targetCookie = cookie;
          break;
        }
      }
    }

    if (targetCookie == null) {
      targetCookie = new Cookie(cookieName, "");
      targetCookie.setPath("/");
      targetCookie.setMaxAge(60 * 60 * 24);
    }

    return targetCookie;
  }
}
