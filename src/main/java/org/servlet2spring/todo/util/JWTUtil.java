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
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JWTUtil {

//  @Value("${org.org.servlet2spring.jwt.secret}")
//  private String key;
//
  private final Key key;
//
//  public JWTUtil(@Value("${org.org.servlet2spring.jwt.secret}") String key) {
//    this.key = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
//  }

  public JWTUtil(@Value("${org.org.servlet2spring.jwt.secret}") String secret) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(Map<String, Object> valueMap, int days) {
    log.info("generateKey : " + key);

    // Header
//    Map<String, Object> headers = new HashMap<>();
//    headers.put("typ", "JWT");
//    headers.put("alg", "HS256");

    // payload
    Map<String, Object> payloads = new HashMap<>();
    payloads.putAll(valueMap);

    // 테스트 시 짧은 유효 기간
    int time = (1) * days;  // 나중에 60 * 24(일 단위)로 변경

    String jwtStr = Jwts.builder()
            //.setHeader(headers)
            .claims(payloads)
            .issuedAt(Date.from(ZonedDateTime.now().toInstant()))
            .expiration(Date.from(ZonedDateTime.now().plusMinutes(time).toInstant()))
//            .signWith(Jwts.SIG.HS256.key().build())  // .signWith(SignatureAlgorithm.HS256, key.getBytes())
//            .signWith(key)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();

    return jwtStr;
  }

  public Map<String, Object> validateToken(String token) throws JwtException {
    Claims claims = Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();

    return claims;
  }
}
