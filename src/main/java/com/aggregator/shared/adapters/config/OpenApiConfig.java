package com.aggregator.shared.adapters.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI aggregatorOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Message Aggregator API from MAI").version("v1.0.0"));
    }
}
