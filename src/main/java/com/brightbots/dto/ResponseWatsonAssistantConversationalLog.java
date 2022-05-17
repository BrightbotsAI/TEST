package com.brightbots.dto;

import java.util.Date;

public class ResponseWatsonAssistantConversationalLog {

    private String watsonChatId;
    private String requestText;
    private Date requestDate;
    private String responseType;
    private String responseText;
    private Date responseDate;
    private String userEmail;

    public ResponseWatsonAssistantConversationalLog(String watsonChatId, String requestText, Date requestDate, String responseType, String responseText, Date responseDate, String userEmail) {
        this.watsonChatId = watsonChatId;
        this.requestText = requestText;
        this.requestDate = requestDate;
        this.responseType = responseType;
        this.responseText = responseText;
        this.responseDate = responseDate;
        this.userEmail = userEmail;
    }

    public String getWatsonChatId() {
        return watsonChatId;
    }

    public void setWatsonChatId(String watsonChatId) {
        this.watsonChatId = watsonChatId;
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
