package org.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.todo.service.TodoService;
import org.servlet2spring.todo.service.TodoService2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
//@WebServlet(name = "todoReadController", value = "/todo/read")
public class TodoReadController extends HttpServlet {

  private TodoService2 todoService2 = TodoService2.INSTANCE;
  private final TodoService todoService;

  @GetMapping({"/read", "/modify"})
  public void read(@RequestParam("no") Long no, Model model) {
    TodoDTO todoDTO = todoService.getOne(no);
    log.info(todoDTO);

    model.addAttribute("dto", todoDTO);
  }

//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    try {
//      Long no = Long.parseLong(req.getParameter("no"));
//      TodoDTO todoDTO = todoService2.get(no);
//
//      // 데이터 담기
//      req.setAttribute("todoDTO", todoDTO);
//
//      // 쿠키 찾기
//      Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
//      String todoList = viewTodoCookie.getValue();
//      boolean exist = false;
//
//      if (todoList != null && todoList.indexOf(no + "-") >= 0) {
//        exist = true;
//      }
//
//      log.info("exist: " + exist);
//
//      if (!exist) {
//        todoList += no + "-";
//        viewTodoCookie.setValue(todoList);
//        viewTodoCookie.setMaxAge(60 * 60 * 24);
//        viewTodoCookie.setPath("/");
//        resp.addCookie(viewTodoCookie);
//      }
//
//      req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
//
//    } catch (Exception e) {
//      e.printStackTrace();
//      log.error(e);
//      throw new ServletException("read error: " + e);
//    }
//  }

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
