package com.brightbots.db.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Table(name = "watson_assistant_chat")
@Entity
@Transactional
public class WatsonAssistantChatEntity implements java.io.Serializable {

    @Id
    @Column(name = "contentChatId", nullable = false, columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger contentChatId;

    @Column(name = "watsonChatId", nullable = false, length = 55)
    private String watsonChatId;

    @Column(name = "userName", nullable = false, length = 150)
    private String userName;

    @Column(name = "userEmail", length = 100)
    private String userEmail;

    @Column(name = "requestTimeStamp", columnDefinition = "TIMESTAMP")
    private Timestamp requestTimeStamp;

    @Column(name = "responseTimeStamp", columnDefinition = "TIMESTAMP")
    private Timestamp responseTimeStamp;

    @Column(name = "requestText", nullable = false, length = 5000)
    private String requestText;

    public BigInteger getContentChatId() {
        return contentChatId;
    }

    public void setContentChatId(BigInteger contentChatId) {
        this.contentChatId = contentChatId;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Timestamp getRequestTimeStamp() {
        return requestTimeStamp;
    }

    public void setRequestTimeStamp(Timestamp requestTimeStamp) {
        this.requestTimeStamp = requestTimeStamp;
    }

    public Timestamp getResponseTimeStamp() {
        return responseTimeStamp;
    }

    public void setResponseTimeStamp(Timestamp responseTimeStamp) {
        this.responseTimeStamp = responseTimeStamp;
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    @Override
    public String toString() {
        return contentChatId + "," + watsonChatId + "," + userName + "," + userEmail + "," + requestTimeStamp + "," + responseTimeStamp + "," + requestText + "\n";
    }
}