package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class Entity {

    public double confidence;
    public ArrayList<Integer> location;
    public String value;
    public String entity;

    public double getConfidence() {
        return confidence;
    }

    public ArrayList<Integer> getLocation() {
        return location;
    }

    public String getValue() {
        return value;
    }

    public String getEntity() {
        return entity;
    }
}