package com.brightbots.db.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Table(name = "microsoft_teams_chat")
@Entity
public class MicrosoftTeamsChats {

    @Id
    @Column(name = "contentChatId", nullable = false, columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger contentChatId;

    @Column(name = "microsoftTeamsChatId", nullable = false, length = 55)
    private String microsoftTeamsChatId;

    @Column(name = "watsonChatId", nullable = false, length = 55)
    private String watsonChatId;

    @Column(name = "userName", nullable = false, length = 150)
    private String userName;

    @Column(name = "textChat", nullable = false, length = 1000)
    private String textChat;

    @Column(name = "dateCreated", columnDefinition = "TIMESTAMP")
    private Timestamp dateCreated;

    @Column(name = "chatURL", nullable = false, length = 150)
    private String chatURL;

    public BigInteger getContentChatId() {
        return contentChatId;
    }

    public void setContentChatId(BigInteger contentChatId) {
        this.contentChatId = contentChatId;
    }

    public String getMicrosoftTeamsChatId() {
        return microsoftTeamsChatId;
    }

    public void setMicrosoftTeamsChatId(String microsoftTeamsChatId) {
        this.microsoftTeamsChatId = microsoftTeamsChatId;
    }

    public String getWatsonChatId() {
        return watsonChatId;
    }

    public void setWatsonChatId(String watsonChatId) {
        this.watsonChatId = watsonChatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTextChat() {
        return textChat;
    }

    public void setTextChat(String textChat) {
        this.textChat = textChat;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getChatURL() {
        return chatURL;
    }

    public void setChatURL(String chatURL) {
        this.chatURL = chatURL;
    }

    @Override
    public String toString() {
        return contentChatId + "," + microsoftTeamsChatId + "," + watsonChatId + "," + userName + "," + textChat + "," + dateCreated + "," + chatURL + "\n";
    }
}
