package org.servlet2spring.util;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.servlet2spring.todo.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class JWTUtilTests {

  @Autowired
  private JWTUtil jwtUtil;

  @Test
  public void testGenerate() {
    Map<String, Object> claimMap = Map.of("mid", "ABCDE");
    String jwtStr = jwtUtil.generateToken(claimMap, 1);

    log.info(jwtStr);
  }

  @Test
  public void testValidate() {

    // 유효시간이 지난 토큰
    String jwtStr = "eyJhbGciOiJIUzI1NiJ9.eyJtaWQiOiJBQkNERSIsImlhdCI6MTc1NjM2ODY1NSwiZXhwIjoxNzU2MzY4NzE1fQ.d8G0ct0uG0Lb4CTSHiXDC64nABtwINF9sOcgLDO6zTY";

    Map<String, Object> claim = jwtUtil.validateToken(jwtStr);

    log.info(claim.toString());
  }

  @Test
  public void testAll() {
    String jwtStr = jwtUtil.generateToken(Map.of("mid", "AAAA", "email", "aaa@bbb.com"), 1);

    log.info(jwtStr);

    Map<String, Object> claim = jwtUtil.validateToken(jwtStr);
    log.info("MID: " + claim.get("mid"));
    log.info("EMAIL: " + claim.get("email"));
  }
}
