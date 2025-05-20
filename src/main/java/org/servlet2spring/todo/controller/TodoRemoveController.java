package org.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.service.TodoService;

@Log4j2
@WebServlet(name = "todoRemoveController", value = "/todo/remove")
public class TodoRemoveController extends HttpServlet {

  private TodoService todoService = TodoService.INSTANCE;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Long no = Long.parseLong(req.getParameter("no"));
    log.info("no: " + no);

    try {
      todoService.remove(no);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ServletException("read error " + e);
    }

    resp.sendRedirect("/todo/list");
  }
}
