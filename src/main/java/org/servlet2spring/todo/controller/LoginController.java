package org.servlet2spring.todo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.java.Log;
import org.servlet2spring.todo.dto.MemberDTO;
//import org.servlet2spring.todo.service.MemberService;
//
//@Log
//@WebServlet("/login")
//public class LoginController extends HttpServlet {
//
//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    log.info("login post...");
//
//    String mid = req.getParameter("mid");
//    String mpw = req.getParameter("mpw");
//
//    String auto = req.getParameter("auto");
//    boolean rememberMe = auto != null && auto.equals("on");
//
//    try {
//      MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpw);
//
//      if (rememberMe) {
//        String uuid = UUID.randomUUID().toString();
//
//        MemberService.INSTANCE.updateUuid(mid, uuid);
//        memberDTO.setUuid(uuid);
//
//        Cookie rememberCookie = new Cookie("remember-me", uuid);
//        rememberCookie.setMaxAge(60 * 60 * 24 * 7); // 1 week
//        rememberCookie.setPath("/");
//
//        resp.addCookie(rememberCookie);
//      }
//
//      HttpSession session = req.getSession();
//      session.setAttribute("loginInfo", memberDTO);
//      resp.sendRedirect("/todo/list");
//    } catch (Exception e) {
//      resp.sendRedirect("/login?result=error");
//    }
//  }
//
//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    log.info("login get...");
//
//    req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
//  }
//}
