package org.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.service.TodoService;
import org.servlet2spring.todo.service.TodoService2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
//@WebServlet(name = "todoRemoveController", value = "/todo/remove")
public class TodoRemoveController extends HttpServlet {

  private final TodoService todoService;
  //private TodoService2 todoService2 = TodoService2.INSTANCE;

  @PostMapping("/remove")
  public String remove(@RequestParam("no") Long no, RedirectAttributes redirectAttributes) {
    log.info("-----remove-----");
    log.info("no: " + no);

    todoService.remove(no);

    return "redirect:/todo/list";
  }
//
//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    Long no = Long.parseLong(req.getParameter("no"));
//    log.info("no: " + no);
//
//    try {
//      todoService2.remove(no);
//    } catch (Exception e) {
//      log.error(e.getMessage());
//      throw new ServletException("read error " + e);
//    }
//
//    resp.sendRedirect("/todo/list");
//  }
}
