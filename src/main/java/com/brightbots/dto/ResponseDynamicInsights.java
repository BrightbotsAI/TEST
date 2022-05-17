package com.brightbots.dto;

public class ResponseDynamicInsights {

    private String searchedText;
    private Long countRecords;

    public ResponseDynamicInsights(String searchedText, Long countRecords) {
        this.searchedText = searchedText;
        this.countRecords = countRecords;
    }

    public String getSearchedText() {
        return searchedText;
    }

    public void setSearchedText(String searchedText) {
        this.searchedText = searchedText;
    }

    public Long getCountRecords() {
        return countRecords;
    }

    public void setCountRecords(Long countRecords) {
        this.countRecords = countRecords;
    }
}
