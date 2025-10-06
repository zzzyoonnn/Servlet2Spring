package org.servlet2spring.todo.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.security.CustomUserDetailsService;
import org.servlet2spring.todo.security.handler.Custom403Handler;
import org.servlet2spring.todo.security.handler.CustomSocialLoginSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

  private final DataSource dataSource;
  private final CustomUserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    log.info("--------------- configure ---------------");

    // 커스텀 로그인 페이지
    http.formLogin(form -> form.loginPage("/member/login"));

    // CSRF 토큰 비활성화
    http.csrf(config -> config.disable());

    http.rememberMe(rememberMe -> rememberMe
            .key("1234")
            .tokenRepository(persistentTokenRepository())
            .userDetailsService(userDetailsService)
            .tokenValiditySeconds(60 * 60 * 24 * 30)
    );

    http.exceptionHandling(exceptions -> exceptions.accessDeniedHandler(accessDeniedHandler()));

    http.oauth2Login(oauth2 -> oauth2.loginPage("/member/login").successHandler(authenticationSuccessHandler()));

    return http.build();
  }

  // 정적 자원 처리
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {

    log.info("--------------- web configure ---------------");

    return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
    return tokenRepository;
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new Custom403Handler();
  }

  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new CustomSocialLoginSuccessHandler(passwordEncoder());
  }
}
