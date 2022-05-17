package com.brightbots.util;

import com.brightbots.db.entity.MicrosoftTeamsChats;
import com.brightbots.db.entity.WatsonAssistantChatEntity;
import com.brightbots.db.entity.WatsonAssistantChatResponses;
import com.brightbots.dto.watsonchat.Generic;
import com.microsoft.graph.models.ChatMessage;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class UtilDTO {

    // Entities
    private WatsonAssistantChatEntity watsonAssistantChatEntity;
    private WatsonAssistantChatResponses watsonAssistantChatResponses;
    private MicrosoftTeamsChats microsoftTeamsChats;


    public WatsonAssistantChatEntity createWatsonAssistantChatEntity(com.brightbots.dto.watsonchat.Log logInd){
        watsonAssistantChatEntity = new WatsonAssistantChatEntity();
        watsonAssistantChatEntity.setWatsonChatId(logInd.getRequest().getContext().getConversation_id());
        watsonAssistantChatEntity.setUserName(logInd.getResponse().getUser_id());
        watsonAssistantChatEntity.setUserEmail("NOT EMAIL YET");
        watsonAssistantChatEntity.setRequestText(logInd.getRequest().getInput().getText());
        watsonAssistantChatEntity.setRequestTimeStamp(new java.sql.Timestamp(logInd.getRequest_timestamp().getTime()));
        watsonAssistantChatEntity.setResponseTimeStamp(new java.sql.Timestamp(logInd.getResponse_timestamp().getTime()));
        return watsonAssistantChatEntity;
    }

    public WatsonAssistantChatResponses createWatsonAssistantChatResponses(Generic indGeneric){
        watsonAssistantChatResponses = new WatsonAssistantChatResponses();
        watsonAssistantChatResponses.setWatsonChatId(watsonAssistantChatEntity);
        watsonAssistantChatResponses.setResponseType(indGeneric.getResponse_type());
        watsonAssistantChatResponses.setResponseText(getResponseText(indGeneric).get());
        return watsonAssistantChatResponses;
    }

    public MicrosoftTeamsChats createMicrosoftTeamsChats(String chatId, AtomicReference<String> watsonChatId, ChatMessage pageInd, String webURL){
        microsoftTeamsChats = new MicrosoftTeamsChats();
        microsoftTeamsChats.setMicrosoftTeamsChatId(chatId);
        microsoftTeamsChats.setWatsonChatId(watsonChatId.get());
        microsoftTeamsChats.setUserName((pageInd.from != null && pageInd.from.user != null && pageInd.from.user.displayName != null ? pageInd.from.user.displayName : "No User Name"));
        microsoftTeamsChats.setTextChat(pageInd.body.content.replaceAll("<p>", "").replaceAll("</p>", "").trim());
        microsoftTeamsChats.setDateCreated((pageInd.createdDateTime != null ? Timestamp.valueOf(pageInd.createdDateTime.toLocalDateTime()) : null));
        microsoftTeamsChats.setChatURL(webURL);
        return microsoftTeamsChats;
    }

    private AtomicReference<String> getResponseText(Generic genericInd) {
        AtomicReference<String> responseText = new AtomicReference<>("");
        switch (genericInd.getResponse_type()) {
            case "search":
                genericInd.getPrimary_results().forEach(indResult -> {
                    responseText.set(responseText + "\"" + indResult.getTitle() + "\" ");
                });
                return responseText;
            case "text":
                return new AtomicReference<>(genericInd.getText());
            case "option":
                genericInd.getOptions().forEach(indOption -> {
                    responseText.set(responseText + "\"" + indOption.getLabel() + "\" ");
                });
                return responseText;
            case "pause":
                return new AtomicReference<>(String.valueOf(genericInd.getTime()));
            case "suggestion":
                genericInd.getSuggestions().forEach(indSuggestion -> {
                    responseText.set(responseText + "\"" + indSuggestion.getLabel() + ": " + indSuggestion.getValue().getInput().getText() + "\" ");
                });
                return responseText;
            default:
                responseText.set("NO TEXT FOR " + genericInd.getResponse_type());
        }
        return responseText;
    }
}
