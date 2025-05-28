package org.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.TodoDTO;
import org.servlet2spring.todo.service.TodoService;
import org.servlet2spring.todo.service.TodoService2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
//@WebServlet(name = "todoModifyController", value = "/todo/modify")
public class TodoModifyController extends HttpServlet {

  private final TodoService todoService;
//  private TodoService2 todoService2 = TodoService2.INSTANCE;
//  private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @PostMapping("/modify")
  public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      log.info("has errors: {}", bindingResult.getAllErrors());

      redirectAttributes.addAttribute("no", todoDTO.getNo());
      return "redirect:/todo/modify";
    }

    log.info(todoDTO);
    todoService.modify(todoDTO);

    return "redirect:/todo/list";
  }

//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    try {
//      Long no = Long.parseLong(req.getParameter("no"));
//      TodoDTO todoDTO = todoService2.get(no);
//
//      // 데이터 담기
//      req.setAttribute("todoDTO", todoDTO);
//      req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req, resp);
//    } catch (Exception e) {
//      log.error(e);
//      throw new ServletException("modify get.... error: " + e);
//    }
//  }
//
//

//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    String finishedStr = req.getParameter("finished");
//
//    TodoDTO todoDTO = TodoDTO.builder()
//            .no(Long.parseLong(req.getParameter("no")))
//            .title(req.getParameter("title"))
//            .dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
//            .finished(finishedStr != null && finishedStr.equals("on"))
//            .build();
//
//    log.info("/todo/modify POST...");
//    log.info(todoDTO);
//
//    try {
//      todoService2.modify(todoDTO);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//    resp.sendRedirect("/todo/list");
//  }
}
