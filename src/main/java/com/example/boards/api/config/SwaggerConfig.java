package com.example.boards.api.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
        @Bean
        public GroupedOpenApi boardApi() {
            String[] paths = {"/**"};

            return GroupedOpenApi.builder()
                    .group("COUPLE API v1")
                    .pathsToMatch(paths)
                    .build();
        }
    }
