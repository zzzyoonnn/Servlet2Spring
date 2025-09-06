package org.servlet2spring.todo.config;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.security.filter.APILoginFilter;
import org.servlet2spring.todo.security.APIUserDetailsService;
import org.servlet2spring.todo.security.filter.RefreshTokenFilter;
import org.servlet2spring.todo.security.filter.TokenCheckFilter;
import org.servlet2spring.todo.security.handler.APILoginSuccessHandler;
import org.servlet2spring.todo.util.JWTUtil;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

  // 주입
  private final APIUserDetailsService apiUserDetailsService;
  private final JWTUtil jwtUtil;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    log.info("---------- web config ----------");

    return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
    log.info("---------- filter chain ---------");

    // AuthenticationManager 설정
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder.class);

    authenticationManagerBuilder.userDetailsService(apiUserDetailsService).passwordEncoder(passwordEncoder());

    // AuthenticationManager 받기
    AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
    http.authenticationManager(authenticationManager);

    // APILoginFilter
    APILoginFilter apiLoginFilter = new APILoginFilter("/generateToken");
    apiLoginFilter.setAuthenticationManager(authenticationManager);

    // APISuccessHandler
    APILoginSuccessHandler successHandler = new APILoginSuccessHandler(jwtUtil);
    apiLoginFilter.setAuthenticationSuccessHandler(successHandler); // SuccessHandler 세팅

    // APILoginFilter 위치 조정
    // api로 시작하는 모든 경로는 TokenCheckFilter 동작
    http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(tokenCheckFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

    // refreshToken 호출 처리
    http.addFilterBefore(new RefreshTokenFilter("/refreshToken", jwtUtil), TokenCheckFilter.class);

    http

            // CSRF 비활성화 (JWT 사용 시 필요)
            .csrf(config -> config.disable())

            // CORS 설정 적용
            .cors(config -> config.configurationSource(corsConfigurationSource()))

            // 세션을 사용하지 않도록 설정 (STATELESS)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // URL 별 접근 권한 설정
            .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/error", "/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                            .requestMatchers("/assets/**", "/css/**", "/js/**").permitAll() // bootstrap
                            .requestMatchers("/board/**", "/replies/**", "/view/**", "/upload/**").permitAll()  // board
                            .requestMatchers("/apiLogin.html", "/generateToken", "/refreshTest.html").permitAll()
                            .requestMatchers("/sample.html").permitAll()
                            .requestMatchers("/api/**").permitAll()
                            .anyRequest().authenticated()   // 그 외 모든 경로는 인증이 필요

            );

    return http.build();
  }

  @Bean
  public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
    return new DefaultMethodSecurityExpressionHandler();
  }

  private TokenCheckFilter tokenCheckFilter(JWTUtil jwtUtil) {
    return new TokenCheckFilter(jwtUtil);
  }

  // CORS 설정
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 출처 허용
    configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")); // 모든 HTTP 메서드 허용
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); // 허용 헤더 목록
    configuration.setAllowCredentials(true); // 자격 증명 허용

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // 모든 경로에 CORS 설정 적용

    return source;
  }
}
