package org.servlet2spring.todo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.security.filter.APILoginFilter;
import org.servlet2spring.todo.security.APIUserDetailsService;
import org.servlet2spring.todo.security.handler.APILoginSuccessHandler;
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

@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

  // 주입
  private final APIUserDetailsService apiUserDetailsService;

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
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

    authenticationManagerBuilder.userDetailsService(apiUserDetailsService).passwordEncoder(passwordEncoder());

    // AuthenticationManager 받기
    AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
    http.authenticationManager(authenticationManager);

    // APILoginFilter
    APILoginFilter apiLoginFilter = new APILoginFilter("/generateToken");
    apiLoginFilter.setAuthenticationManager(authenticationManager);

    // APISuccessHandler
    APILoginSuccessHandler successHandler = new APILoginSuccessHandler();
    apiLoginFilter.setAuthenticationSuccessHandler(successHandler); // SuccessHandler 세팅

    // APILoginFilter 위치 조정
    http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);

    http
            .csrf(csrf -> csrf.disable()) // Lambda DSL 사용
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/error", "/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                            .requestMatchers("/assets/**","/css/**","/js/**").permitAll() // bootstrap
                            .requestMatchers("/board/**", "/replies/**", "/view/**", "/upload/**").permitAll()  // board
                            .requestMatchers("/", "/apiLogin.html", "/apiLogin").permitAll()
                            .anyRequest().authenticated()

            );

    return http.build();
  }

  @Bean
  public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
    return new DefaultMethodSecurityExpressionHandler();
  }
}
