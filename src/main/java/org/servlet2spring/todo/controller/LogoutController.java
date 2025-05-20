package org.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/logout")
public class LogoutController extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("log out...");

    HttpSession session = req.getSession();
    session.removeAttribute("loginInfo");
    session.invalidate();

    resp.sendRedirect("/");
  }
}
