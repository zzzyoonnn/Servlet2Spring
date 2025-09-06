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
    registry.addResourceHandler("/**")
    //registry.addResourceHandler("/assets/**", "/css/**", "/js/**", "/upload/**", "/**", "/generateToken", "/**")
            .addResourceLocations("classpath:/static/", "file:/Users/jiyoon/Documents/upload/");
  }
}
