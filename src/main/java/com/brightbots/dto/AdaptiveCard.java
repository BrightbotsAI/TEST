package com.brightbots.dto;

public class AdaptiveCard {

    private final String userName;
    private final String userQuery;
    private final String userEmail;
    private final String imageURL;
    private final String webPageURL;
    private final String userBrowser;
    private final String userIP;
    private final String watsonAssistanChatId;

    public AdaptiveCard(String userName, String userQuery, String userEmail, String imageURL, String webPageURL, String userBrowser, String userIP, String watsonAssistanChatId) {
        this.userName = userName;
        this.userQuery = userQuery;
        this.userEmail = userEmail;
        this.imageURL = imageURL;
        this.webPageURL = webPageURL;
        this.userBrowser = userBrowser;
        this.userIP = userIP;
        this.watsonAssistanChatId = watsonAssistanChatId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserQuery() {
        return userQuery;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getWebPageURL() {
        return webPageURL;
    }

    public String getUserBrowser() {
        return userBrowser;
    }

    public String getUserIP() {
        return userIP;
    }

    public String getWatsonAssistanChatId() {
        return watsonAssistanChatId;
    }
}
