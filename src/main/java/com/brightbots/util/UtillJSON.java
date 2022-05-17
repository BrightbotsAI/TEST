package com.brightbots.util;

import com.brightbots.dto.AdaptiveCard;
import com.brightbots.processrequest.ProcessRequestBox;
import com.brightbots.teams.MicrosoftGraphClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.microsoft.graph.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Logger;

@Component
public class UtillJSON {

    private static final Logger log = Logger.getLogger(UtillJSON.class.getName());

    @Autowired
    private Environment env;
    private JsonObject jsonAdptiveCard;
    private JsonArray sectionsArray;
    private JsonObject section;
    private JsonArray factsArray;
    private JsonObject facts;
    private JsonArray potentialActionsArray;
    private JsonObject potentialAction;
    private JsonArray targetsActionsArray;
    private JsonObject target;
    private final String topicName = "Requesting TRM Live Agent";
    private final String messageToAgent = "[%s] Hi %s, TRM Live Agent here, how can i help you ?";

    public JsonObject createAdaptiveCard(AdaptiveCard adaptiveCard) throws UnsupportedEncodingException {
        jsonAdptiveCard = new JsonObject();
        jsonAdptiveCard.addProperty("@type", "MessageCard");
        jsonAdptiveCard.addProperty("@context", "http://schema.org/extensions");
        jsonAdptiveCard.addProperty("themeColor", "0076D7");
        jsonAdptiveCard.addProperty("summary", " New Live Chat from: " + adaptiveCard.getUserName());
        sectionsArray = new JsonArray();
        section = new JsonObject();
        section.addProperty("activityTitle", " New Live Chat from: " + adaptiveCard.getUserName());
        section.addProperty("activitySubtitle", "On Watson Assistant - by Brightbots. \nTo respond, click Join Chat or open the chat console.");
        section.addProperty("activityImage", (!adaptiveCard.getImageURL().equals("") ? adaptiveCard.getImageURL() : env.getProperty("teams.webhook.defaultImageURL")));
        factsArray = new JsonArray();
        facts = new JsonObject();
        facts.addProperty("name", "Name");
        facts.addProperty("value", adaptiveCard.getUserName());
        factsArray.add(facts);
        facts = new JsonObject();
        facts.addProperty("name", "Email");
        facts.addProperty("value", adaptiveCard.getUserEmail());
        factsArray.add(facts);
        facts = new JsonObject();
        facts.addProperty("name", "Visitor Question");
        facts.addProperty("value", adaptiveCard.getUserQuery());
        factsArray.add(facts);
        facts = new JsonObject();
        facts.addProperty("name", "Page");
        facts.addProperty("value", adaptiveCard.getWebPageURL());
        factsArray.add(facts);
        facts = new JsonObject();
        facts.addProperty("name", "Browser");
        facts.addProperty("value", adaptiveCard.getUserBrowser());
        factsArray.add(facts);
        facts = new JsonObject();
        facts.addProperty("name", "IP");
        facts.addProperty("value", adaptiveCard.getUserIP());
        factsArray.add(facts);
        section.add("facts", factsArray);
        section.addProperty("markdown", "true");
        sectionsArray.add(section);
        jsonAdptiveCard.add("sections", sectionsArray);
        potentialActionsArray = new JsonArray();
        potentialAction = new JsonObject();
        potentialAction.addProperty("@type", "OpenUri");
        potentialAction.addProperty("name", "Join Chat");
        targetsActionsArray = new JsonArray();
        target = new JsonObject();
        target.addProperty("os", "default");
        String URI = "https://teams.microsoft.com/l/chat/0/0?users=" + adaptiveCard.getUserEmail() + "&topicName="+ URLEncoder.encode(topicName, "UTF-8").replace("+", "%20")
                + "&message=" + URLEncoder.encode(String.format(messageToAgent, adaptiveCard.getWatsonAssistanChatId(), adaptiveCard.getUserName()), "UTF-8").replace("+", "%20");
        log.info(URI);
        target.addProperty("uri", URI);
        targetsActionsArray.add(target);
        potentialAction.add("targets", targetsActionsArray);
        potentialActionsArray.add(potentialAction);
        jsonAdptiveCard.add("potentialAction", potentialActionsArray);
        return jsonAdptiveCard;
    }
}
