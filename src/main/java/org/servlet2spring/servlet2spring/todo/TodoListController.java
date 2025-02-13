package org.servlet2spring.servlet2spring.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.servlet2spring.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.servlet2spring.todo.service.TodoService;

@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("/todo/list");

    List<TodoDTO> dtoList = TodoService.INSTANCE.getList();

    req.setAttribute("todoList", dtoList);

    req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
  }
}
