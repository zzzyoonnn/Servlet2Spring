package org.servlet2spring.todo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.dto.MemberDTO;
//import org.servlet2spring.todo.service.MemberService;
//
//@Log4j2
//@WebFilter(urlPatterns = {"/todo/*"})
//public class LoginCheckFilter implements Filter {
//
//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//    log.info("Login check filter...");
//
//    HttpServletRequest req = (HttpServletRequest) request;
//    HttpServletResponse resp = (HttpServletResponse) response;
//
//    HttpSession session = req.getSession();
//
//    if (session.getAttribute("loginInfo") != null) {
//      chain.doFilter(request, response);
//      return;
//    }
//
//    // session에 loginInfo 값이 없다면 쿠키 확인
//    Cookie cookie = findCookie(req.getCookies(), "remember-me");
//
//    // 세션에도 없고 쿠키도 없다면 로그인
//    if (cookie == null) {
//      resp.sendRedirect("/login");
//      return;
//    }
//
//    // 쿠키가 존재
//    log.info("cookie는 존재");
//
//    // uuid
//    String uuid = cookie.getValue();
//
//    try {
//      // 데이터베이스 확인
//      MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(uuid);
//
//      log.info("쿠키의 값으로 조회한 사용자 정보: " + memberDTO);
//      if(memberDTO == null) {
//        throw new Exception("Cookie value is not valid.");
//      }
//
//      // 회원 정보를 세션에 추가
//      session.setAttribute("loginInfo", memberDTO);
//      chain.doFilter(request, response);
//
//    } catch (Exception e) {
//      e.printStackTrace();
//      resp.sendRedirect("/login");
//    }
//  }
//
//  private Cookie findCookie(Cookie[] cookies, String name) {
//
//    if (cookies == null || cookies.length == 0) {
//      return null;
//    }
//
//    Optional<Cookie> result = Arrays.stream(cookies)
//            .filter(ck -> ck.getName().equals(name))
//            .findFirst();
//
//    return result.isPresent() ? result.get() : null;
//  }
//}
