package com.brightbots.dto.watsonchat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookResultMSTeams {

    public String message;
    private ResponseUnion response;

    @JsonProperty("response")
    public ResponseUnion getResponse() { return response; }
    public String getMessage() {
        return message;
    }
}