package org.servlet2spring.todo.util;


import io.jsonwebtoken.JwtException;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JWTUtil {

  @Value("${org.org.servlet2spring.jwt.secret}")
  private String key;

  public String generateToken(Map<String, Object> valueMap, int days) {
    log.info("generateKey : " + key);

    return null;
  }

  public Map<String, Object> validateToken(String token) throws JwtException {
    Map<String, Object> claim = null;

    return claim;
  }
}
