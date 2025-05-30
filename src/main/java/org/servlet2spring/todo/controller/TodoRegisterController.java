package org.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
public class TodoRegisterController extends HttpServlet {

  private final TodoService todoService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("/todo/register GET...");

    HttpSession session = req.getSession();

    if (session.isNew()) {
      log.info("JSESSIONID 쿠키가 새로 만들어진 사용자: " + session.getId());
      resp.sendRedirect("/login");
      return;
    }

    // JSESSIONID는 있지만 해당 세션 컨텍스트에 loginInfo라는 이름으로 저장된 객체가 없는 경우
    if (session.getAttribute("loginInfo") == null) {
      log.info("로그인한 정보가 없는 사용자");
      resp.sendRedirect("/login");
      return;
    }

    // 정상적인 경우
    req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
  }

  @PostMapping("/register")
  public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
    log.info("POST todo register...");

    if (bindingResult.hasErrors()) {
      log.info("has errors: " + bindingResult.getAllErrors());
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

      return "redirect:/todo/register";
    }

    log.info(todoDTO);

    todoService.register(todoDTO);
    return "redirect:/todo/list";
  }
}