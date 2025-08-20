package org.servlet2spring.todo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

  // API 문서화할 경로 및 그룹 설정
  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
            .group("public")
            .pathsToMatch("/**") // 모든 경로 문서화
            .packagesToScan("org.servlet2spring.todo.controller") // 컨트롤러 패키지 지정
            .build();
  }

  // Swagger UI에 보여질 기본 정보
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .info(new Info()
                    .title("Boot API 01 Project Swagger")
                    .version("1.0")
                    .description("Spring Boot 3 + Springdoc OpenAPI 적용 예제"));
  }
}
