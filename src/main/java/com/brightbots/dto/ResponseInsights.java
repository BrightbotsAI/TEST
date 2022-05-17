package com.brightbots.dto;

public class ResponseInsights {

    private int responseCode;
    private String responseMessage;
    private InsightsDTO dynamicInsight;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public InsightsDTO getDynamicInsight() {
        return dynamicInsight;
    }

    public void setDynamicInsight(InsightsDTO dynamicInsight) {
        this.dynamicInsight = dynamicInsight;
    }
}
