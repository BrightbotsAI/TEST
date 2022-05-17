package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class Input {

    public Options options;
    public String message_type;
    public Source source;
    public String text;
    public ArrayList<Intent> intents;
    public ArrayList<Entity> entities;
    public String suggestion_id;

    public Options getOptions() {
        return options;
    }

    public String getMessage_type() {
        return message_type;
    }

    public Source getSource() {
        return source;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Intent> getIntents() {
        return intents;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public String getSuggestion_id() {
        return suggestion_id;
    }
}
