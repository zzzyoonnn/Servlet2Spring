package org.servlet2spring.todo.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
//import org.servlet2spring.todo.security.exception.AccessTokenException;
//import org.servlet2spring.todo.security.exception.AccessTokenException.TOKEN_ERROR;
import org.springframework.web.filter.OncePerRequestFilter;

@Log4j2
@RequiredArgsConstructor
public class LegacyTokenCheckFilter {//extends OncePerRequestFilter {
//
//  private final JWTUtil jwtUtil;
//  private final APIUserDetailsService apiUserDetailsService;
//
//  private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException {
//    String headerStr = request.getHeader("Authorization");
//
//    if (headerStr == null || headerStr.length() < 8) {
//      throw new AccessTokenException(TOKEN_ERROR.UNACCEPT);
//    }
//
//    // Bearer 생략
//    String tokenType = headerStr.substring(0, 6);
//    String tokenStr = headerStr.substring(7);
//
//    if (tokenType.equalsIgnoreCase("Bearer") == false) {
//      throw new AccessTokenException(TOKEN_ERROR.BADTYPE);
//    }
//
//    try {
//      Map<String, Object> values = jwtUtil.validateToken(tokenStr);
//
//      return values;
//    } catch (MalformedJwtException e) {
//      log.error("---------- MalformedJwtException ----------");
//      throw new AccessTokenException(TOKEN_ERROR.MALFORM);
//    } catch (SignatureException e) {
//      log.error("---------- SignatureException ----------");
//      throw new AccessTokenException(TOKEN_ERROR.BADSIGN);
//    } catch (ExpiredJwtException e) {
//      log.error("---------- ExpiredJwtException ----------");
//      throw new AccessTokenException(TOKEN_ERROR.EXPIRED);
//    }
//  }
//
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//          throws ServletException, IOException {
//    String path = request.getRequestURI();
//
//    if (!path.startsWith("/api/") && !path.startsWith("/tokens/generateToken")) {
//      filterChain.doFilter(request, response);
//      return;
//    }
//
//    log.info("---------- Token Check Filter ----------");
//    log.info("JWTUtil: " + jwtUtil.toString());
//
//    try {
//      Map<String, Object> payload = validateAccessToken(request);
//
//      // mid
//      String mid = (String) payload.get("mid");
//
//      log.info("mid: " + mid);
//
//      // 사용자 정보 로드
//      UserDetails userDetails = apiUserDetailsService.loadUserByUsername(mid);
//
//      // Authentication 객체 생성
//      UsernamePasswordAuthenticationToken authenticationToken =
//              new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//      filterChain.doFilter(request, response);
//    } catch (AccessTokenException e) {
//      e.sendResponseError(response);
//    }
//  }
}
