package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class Value {
    public Input input;
    public ArrayList<Intent> intents;
    public ArrayList<Entity> entities;

    public Input getInput() {
        return input;
    }

    public ArrayList<Intent> getIntents() {
        return intents;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
