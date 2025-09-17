package org.servlet2spring.todo.security.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class LegacyAPILoginSuccessHandler {//implements AuthenticationSuccessHandler {
//
//  private final JWTUtil jwtUtil;
//
//  @Override
//  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//    log.info("---------- Login success handler ----------");
//
//    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//    log.info(authentication);
//    log.info(authentication.getName());   // username
//
//    Map<String, Object> claim = Map.of("mid", authentication.getName());
//
//    // Access Token 유효 기간 1일
//    String accessToken = jwtUtil.generateToken(claim, 1);
//
//    // Refresh Token 유효 기간 30일
//    String refreshToken = jwtUtil.generateToken(claim, 30);
//
//    Gson gson = new Gson();
//
//    Map<String, String> keyMap = Map.of("accessToken", accessToken, "refreshToken", refreshToken);
//
//    String jsonStr = gson.toJson(keyMap);
//
//    httpServletResponse.getWriter().println(jsonStr);
//  }
}
