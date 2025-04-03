package org.servlet2spring.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.java.Log;
import org.servlet2spring.servlet2spring.todo.dto.MemberDTO;
import org.servlet2spring.servlet2spring.todo.service.MemberService;

@Log
@WebServlet("/login")
public class LoginController extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("login post...");

    String mid = req.getParameter("mid");
    String mpw = req.getParameter("mpw");

    try {
      MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpw);
      HttpSession session = req.getSession();
      session.setAttribute("loginInfo", memberDTO);
      resp.sendRedirect("/todo/list");
    } catch (Exception e) {
      resp.sendRedirect("/login?result=error");
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("login get...");

    req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
  }
}
