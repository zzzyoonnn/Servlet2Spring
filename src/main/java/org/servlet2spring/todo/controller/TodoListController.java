package org.servlet2spring.todo.controller;

import jakarta.servlet.http.HttpServlet;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/todo")
//@WebServlet(name = "todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {
  private TodoService todoService = TodoService.INSTANCE;

  @RequestMapping("/list")
  public void list() {
    log.info("todo list...");
  }

  //@RequestMapping(value = "/register", method = RequestMethod.GET)
  @GetMapping("/register")
  public void registerGET() {
    log.info("GET todo register...");
  }


//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    log.info("todoList...");
//
//    ServletContext servletContext = req.getServletContext();
//
//    log.info("appName: " + servletContext.getAttribute("appName"));
//
//    try {
//      List<TodoDTO> dtoList = todoService.listAll();
//      req.setAttribute("dtoList", dtoList);
//      req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
//    } catch (Exception e) {
//      log.error(e.getMessage());
//      throw new ServletException("list error");
//    }
//  }
}
