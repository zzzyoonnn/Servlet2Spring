package org.servlet2spring.todo.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JWTUtil {

  private final SecretKey key;

  public JWTUtil(@Value("${org.org.servlet2spring.jwt.secret}") String secret) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(Map<String, Object> valueMap, int days) {
    log.info("generateKey : " + key);

    Map<String, Object> payloads = new HashMap<>();
    payloads.putAll(valueMap);

    // 테스트 시 짧은 유효 기간
    int time = (60 * 24) * days;  // 일 단위

    String jwtStr = Jwts.builder()
            .claims(payloads)
            .issuedAt(Date.from(ZonedDateTime.now().toInstant()))
            .expiration(Date.from(ZonedDateTime.now().plusMinutes(time).toInstant()))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

    return jwtStr;
  }

  public Map<String, Object> validateToken(String token) throws JwtException {
    Claims claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();

    log.info("Validating token: " + token);

    return claims;
  }
}
