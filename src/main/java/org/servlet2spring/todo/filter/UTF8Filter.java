package org.servlet2spring.todo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebFilter(urlPatterns = {"/*"})
public class UTF8Filter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.info("UTF8 filter...");

    HttpServletRequest req = (HttpServletRequest) request;
    req.setCharacterEncoding("UTF-8");

    chain.doFilter(request, response);
  }

}
