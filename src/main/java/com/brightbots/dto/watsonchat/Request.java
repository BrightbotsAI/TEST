package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class Request {

    public Input input;
    public Context context;
    public ArrayList<String> actions;

    public Input getInput() {
        return input;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<String> getActions() {
        return actions;
    }
}
