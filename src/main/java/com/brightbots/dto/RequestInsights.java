package com.brightbots.dto;

import java.util.Date;

public class RequestInsights {

    private String fromDate;
    private String toDate;
    private String typeResponse;
    private String queryText;
    private String watsonChatId;


    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getTypeResponse() {
        return typeResponse;
    }

    public void setTypeResponse(String typeResponse) {
        this.typeResponse = typeResponse;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public String getWatsonChatId() {
        return watsonChatId;
    }

    public void setWatsonChatId(String watsonChatId) {
        this.watsonChatId = watsonChatId;
    }
}
