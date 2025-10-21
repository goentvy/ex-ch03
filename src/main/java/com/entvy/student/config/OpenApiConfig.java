package com.entvy.student.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI studentOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("학생 관리 API")
                        .description("Spring Boot + MyBatis 기반 학생 CRUD API 문서입니다.")
                        .version("v1.0.0"));
    }
}
