package org.servlet2spring.todo.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.security.exception.AccessTokenException;
import org.servlet2spring.todo.security.exception.AccessTokenException.TOKEN_ERROR;
import org.servlet2spring.todo.util.JWTUtil;
import org.springframework.web.filter.OncePerRequestFilter;

@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {

  private final JWTUtil jwtUtil;

  private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException {
    String headerStr = request.getHeader("Authorization");

    if (headerStr == null || headerStr.length() < 8) {
      throw new AccessTokenException(TOKEN_ERROR.UNACCEPT);
    }

    // Bearer 생략
    String tokenType = headerStr.substring(0, 6);
    String tokenStr = headerStr.substring(7);

    if (tokenType.equalsIgnoreCase("Bearer") == false) {
      throw new AccessTokenException(TOKEN_ERROR.BADTYPE);
    }

    try {
      Map<String, Object> values = jwtUtil.validateToken(tokenStr);

      return values;
    } catch (MalformedJwtException e) {
      log.error("---------- MalformedJwtException ----------");
      throw new AccessTokenException(TOKEN_ERROR.MALFORM);
    } catch (SignatureException e) {
      log.error("---------- SignatureException ----------");
      throw new AccessTokenException(TOKEN_ERROR.BADSIGN);
    } catch (ExpiredJwtException e) {
      log.error("---------- ExpiredJwtException ----------");
      throw new AccessTokenException(TOKEN_ERROR.EXPIRED);
    }
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
    String path = request.getRequestURI();

    if (!path.startsWith("/api/") && !path.startsWith("/tokens/generateToken")) {
      filterChain.doFilter(request, response);
      return;
    }

    log.info("---------- Token Check Filter ----------");
    log.info("JWTUtil: " + jwtUtil.toString());

    try {
      validateAccessToken(request);
      filterChain.doFilter(request, response);
    } catch (AccessTokenException e) {
      e.sendResponseError(response);
    }
  }
}
