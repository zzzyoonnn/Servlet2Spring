package org.servlet2spring.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.servlet2spring.todo.service.TodoService;

@Log4j2
@WebServlet(name = "todoRegisterController", value = "/todo/register")
public class TodoRegisterController extends HttpServlet {

  private TodoService todoService = TodoService.INSTANCE;
  private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("/todo/register GET...");
    req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    TodoDTO todoDTO = TodoDTO.builder()
            .title(req.getParameter("title"))
            .dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
            .build();

    log.info("/todo/register POST...");
    log.info(todoDTO);

    try {
      todoService.register(todoDTO);
    } catch (Exception e) {
      e.printStackTrace();
    }

    resp.sendRedirect("/todo/list");
  }
}
