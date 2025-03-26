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
@WebServlet(name = "todoModifyController", value = "/todo/modify")
public class TodoModifyController extends HttpServlet {

  private TodoService todoService = TodoService.INSTANCE;
  private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Long no = Long.parseLong(req.getParameter("no"));
      TodoDTO todoDTO = todoService.get(no);

      // 데이터 담기
      req.setAttribute("todoDTO", todoDTO);
      req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req, resp);
    } catch (Exception e) {
      log.error(e);
      throw new ServletException("modify get.... error: " + e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String finishedStr = req.getParameter("finished");

    TodoDTO todoDTO = TodoDTO.builder()
            .no(Long.parseLong(req.getParameter("no")))
            .title(req.getParameter("title"))
            .dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
            .finished(finishedStr != null && finishedStr.equals("on"))
            .build();

    log.info("/todo/modify POST...");
    log.info(todoDTO);

    try {
      todoService.modify(todoDTO);
    } catch (Exception e) {
      e.printStackTrace();
    }

    resp.sendRedirect("/todo/list");
  }
}
