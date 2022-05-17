package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public
class Response {

    public OutputResponse output;
    public ArrayList<Intent> intents;
    public ArrayList<Entity> entities;
    public String user_id;
    public Context context;
    public Input input;
    public ArrayList<String> actions;

    public OutputResponse getOutput() {
        return output;
    }

    public ArrayList<Intent> getIntents() {
        return intents;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public String getUser_id() {
        return user_id;
    }

    public Context getContext() {
        return context;
    }

    public Input getInput() {
        return input;
    }

    public ArrayList<String> getActions() {
        return actions;
    }
}

