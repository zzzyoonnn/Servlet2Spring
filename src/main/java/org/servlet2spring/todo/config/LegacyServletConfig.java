package org.servlet2spring.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class LegacyServletConfig implements WebMvcConfigurer {

//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("/**")
//            .addResourceLocations("classpath:/static/", "file:/Users/jiyoon/Documents/upload/");
//  }
}
