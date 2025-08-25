package org.servlet2spring.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CustomServletConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/**", "/css/**", "/js/**", "/upload/**", "/apiLogin.html")
            .addResourceLocations("classpath:/static/assets/", "classpath:/static/css/", "classpath:/static/js/", "file:/Users/jiyoon/Documents/upload/", "classpath:/static/");

  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // /apiLogin → /apiLogin.html 매핑
    registry.addViewController("/apiLogin").setViewName("forward:/apiLogin.html");
  }
}
