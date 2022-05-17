package com.brightbots.db.entity;



import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Table(name = "watson_assistant_chat_responses")
@Entity
@Transactional
public class WatsonAssistantChatResponses implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatResponseId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "watsonChatId", nullable = false)
    private WatsonAssistantChatEntity watsonChatId;

    @Column(name = "responseType", nullable = false, length = 50)
    private String responseType;

    @Column(name = "responseText", nullable = false, length = 5000)
    private String responseText;

    public WatsonAssistantChatEntity getWatsonChatId() {
        return watsonChatId;
    }

    public void setWatsonChatId(WatsonAssistantChatEntity watsonChatId) {
        this.watsonChatId = watsonChatId;
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

    @Override
    public String toString() {
        return chatResponseId + "," + watsonChatId.getWatsonChatId() + "," + responseType + "," + responseText.replaceAll("\n" , " ") + "\n";
    }
}
