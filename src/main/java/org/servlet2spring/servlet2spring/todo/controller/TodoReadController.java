package org.servlet2spring.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.servlet2spring.todo.service.TodoService;

@Log4j2
@WebServlet(name = "todoReadController", value = "/todo/read")
public class TodoReadController extends HttpServlet {

  private TodoService todoService = TodoService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Long no = Long.parseLong(req.getParameter("no"));
      TodoDTO todoDTO = todoService.get(no);

      // 데이터 담기
      req.setAttribute("todoDTO", todoDTO);
      req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
    } catch (Exception e) {
      log.error(e);
      throw new ServletException("read error: " + e);
    }
  }
}
