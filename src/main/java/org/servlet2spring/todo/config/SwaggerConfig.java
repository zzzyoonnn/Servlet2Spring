package org.servlet2spring.todo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

  private static final String SCHEME_NAME = "Bearer Authentication";
  private static final String SCHEME = "bearer";

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
            // API 정보 설정
            .info(new Info()
                    .title("Boot API 01 Project")
                    .version("1.0.0")
                    .description("Boot API 01 프로젝트의 API 문서입니다.")
            )
            // Components에 SecurityScheme(인증 방식) 추가
            .components(new Components()
                    .addSecuritySchemes(SCHEME_NAME, new SecurityScheme()
                            .name(SCHEME_NAME)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme(SCHEME)
                            .bearerFormat("JWT") // Bearer Token 포맷 지정
                    )
            )
            // 모든 API 경로에 SecurityRequirement(보안 요구사항) 추가
            .addSecurityItem(new SecurityRequirement()
                    .addList(SCHEME_NAME)
            );
  }
}
