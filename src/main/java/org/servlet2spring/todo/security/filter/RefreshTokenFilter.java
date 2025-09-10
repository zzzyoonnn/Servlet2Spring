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
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.security.exception.RefreshTokenException;
import org.servlet2spring.todo.security.exception.RefreshTokenException.ErrorCase;
import org.servlet2spring.todo.util.JWTUtil;
import org.springframework.http.MediaType;
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

      // 새로운 Access Token 발행

      // Refresh Token의 유효 시간이 얼마 남지 않은 경우
      Long expLong = (Long) refreshClaims.get("exp");
      Integer exp = expLong.intValue();

      Date expTime = new Date(Instant.ofEpochMilli(exp).toEpochMilli() * 1000);
      Date current = new Date(System.currentTimeMillis());

      // 만료 시간과 현재 시간의 간격 계산
      // 만약 3일 미만일 경우 Refresh Token도 다시 생성
      long gapTime = (expTime.getTime() - current.getTime());

      log.info("------------------------------");
      log.info("current :" + current);
      log.info("exp: " + expTime);
      log.info("gap: " + gapTime);

      String mid = (String)refreshClaims.get("mid");

      // 여기에 도달했으면 무조건 Access Token 새로 생성
      String accessTokenValue = jwtUtil.generateToken(Map.of("mid", mid), 1);
      String refreshTokenValue = tokens.get("refreshToken");

      // refreshToken이 3일도 안남았다면
      if (gapTime < (1000 * 60 * 60 * 24  * 3)) {
        log.info("new Refresh Token required...");
        refreshTokenValue = jwtUtil.generateToken(Map.of("mid", mid), 1);
      }

      log.info("Refresh Token result --------------------");
      log.info("accessToken: " + accessTokenValue);
      log.info("refreshToken: " + refreshTokenValue);

      sendTokens(accessTokenValue, refreshTokenValue, response);

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

  private void sendTokens(String accessTokenValue, String refreshTokenValue, HttpServletResponse response) {

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    Gson gson = new Gson();

    String jsonStr = gson.toJson(Map.of("accessToken", accessTokenValue, "RefreshToken", refreshTokenValue));

    try {
      response.getWriter().println(jsonStr);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
