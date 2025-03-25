package org.servlet2spring.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import jakarta.servlet.annotation.WebServlet;
import org.servlet2spring.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.servlet2spring.todo.service.TodoService;

@Log4j2
@WebServlet(name = "todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {
  private TodoService todoService = TodoService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("todoList...");

    try {
      List<TodoDTO> dtoList = todoService.listAll();
      req.setAttribute("dtoList", dtoList);
      req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ServletException("list error");
    }
  }
}
