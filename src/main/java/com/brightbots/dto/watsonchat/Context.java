package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class Context {

    public Metadata metadata;
    public System system;
    public String conversation_id;
    public Integrations integrations;
    public String summary;
    public WebhookResultMSTeams webhook_result_MSTeams;
    public String name;
    public ArrayList<String> email;

    public Metadata getMetadata() {
        return metadata;
    }

    public System getSystem() {
        return system;
    }

    public String getConversation_id() {
        return conversation_id;
    }

    public Integrations getIntegrations() {
        return integrations;
    }

    public String getSummary() {
        return summary;
    }

    public WebhookResultMSTeams getWebhook_result_MSTeams() {
        return webhook_result_MSTeams;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getEmail() {
        return email;
    }
}