package com.brightbots;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.brightbots.db")
public class SBApplication {
    @Bean
    public GroupedOpenApi actuatorOpenApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/health", "/BoxService/getSharedLink/{fileId}", "/TeamsService/sendNotificationToAgent", "/DBService/saveUserChat" , "/InsightsService/generateInsights")
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(SBApplication.class, args);
    }
}
