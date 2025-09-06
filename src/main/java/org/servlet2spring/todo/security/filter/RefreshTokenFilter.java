package org.servlet2spring.todo.security.filter;

import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.security.exception.RefreshTokenException;
import org.servlet2spring.todo.security.exception.RefreshTokenException.ErrorCase;
import org.servlet2spring.todo.util.JWTUtil;
import org.springframework.web.filter.OncePerRequestFilter;

@Log4j2
@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {

  private final String refreshPath;
  private final JWTUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String path = request.getRequestURI();

    if (!path.equals(refreshPath)) {
      log.info("---------- skip refresh token filter ----------");
      filterChain.doFilter(request, response);
      return;
    }

    log.info("---------- Refresh token filter ----------");

    // 전송된 JSON에서 accessToken과 refreshToken을 받아온다.
    Map<String, String> tokens = parseRequestJSON(request);

    String accessToken = tokens.get("accessToken");
    String refreshToken = tokens.get("refreshToken");

    log.info("accessToken: " + accessToken);
    log.info("refreshToken: " + refreshToken);

    try {
      checkAccessToken(accessToken);

    } catch (RefreshTokenException refreshTokenException) {
      refreshTokenException.sendResponseError(response);
    }

    Map<String, Object> refreshClaims = null;

    try {
      refreshClaims = checkRefreshToken(refreshToken);
      log.info(refreshClaims);

    } catch (RefreshTokenException refreshTokenException) {
      refreshTokenException.sendResponseError(response);
      return;
    }
  }

  private Map<String, String> parseRequestJSON(HttpServletRequest request) {

    // JSON 데이터를 분석해서 mid, mpw 전달 값을 Map으로 처리한다.
    try (Reader reader = new InputStreamReader(request.getInputStream())) {
      Gson gson = new Gson();

      return gson.fromJson(reader, Map.class);
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    return null;
  }

  private void checkAccessToken(String accessToken) throws RefreshTokenException {
    try {
      jwtUtil.validateToken(accessToken);

    } catch (ExpiredJwtException expiredJwtException) {
      log.info("Access token has expired.");

    } catch (Exception exception) {
      throw new RefreshTokenException(ErrorCase.NO_ACCESS);
    }
  }

  private Map<String, Object> checkRefreshToken(String refreshToken) throws RefreshTokenException {
    try {
      Map<String, Object> values = jwtUtil.validateToken(refreshToken);
      return values;

    } catch (ExpiredJwtException expiredJwtException) {
      throw new RefreshTokenException(ErrorCase.OLD_REFRESH);

    } catch (MalformedJwtException malformedJwtException) {
      log.error("MalformedJwtException --------------------");
      throw new RefreshTokenException(ErrorCase.NO_REFRESH);

    } catch (Exception exception) {
      new RefreshTokenException(ErrorCase.NO_REFRESH);
    }

    return null;
  }
}
