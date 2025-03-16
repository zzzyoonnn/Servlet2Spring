package org.servlet2spring.servlet2spring.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.servlet2spring.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.servlet2spring.todo.service.TodoService;

@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("/todo/read");

    Long no = Long.parseLong(req.getParameter("no"));
    //TodoDTO todoDTO = TodoService.INSTANCE.get(no);
    //req.setAttribute("todoDTO", todoDTO);
    req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
  }
}
