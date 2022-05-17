package com.brightbots;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class SpringProperties {
    @Value("${teams.webhook.url}")
    private String teamsWebhookURL;

    @Value("${teams.webhook.defaultImageURL}")
    private String defaultImageURL;

    @Value("${ibm.watsonassistant.url.main}")
    private String watsonAssistantURLM;

    @Value("${ibm.watsonassistant.url.final}")
    private String watsonAssistantURLF;

    @Value("${ibm.watsonassistant.apikey}")
    private String watsonAssistantAPIKey;

    @Value("${teams.client.id}")
    private String teamsClientID;

    @Value("${teams.user.name}")
    private String teamsUserName;

    @Value("${teams.user.pswd}")
    private String teamsPassword;
}
