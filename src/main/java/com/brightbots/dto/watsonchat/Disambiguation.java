package com.brightbots.dto.watsonchat;

public class Disambiguation {
    public boolean alternate_responses;
    private String significant_influence;
    private String model_type;
    private String model_id;
    private String outcome;
    private String influence;

    public boolean isAlternate_responses() {
        return alternate_responses;
    }

    public String getSignificant_influence() {
        return significant_influence;
    }

    public String getModel_type() {
        return model_type;
    }

    public String getModel_id() {
        return model_id;
    }

    public String getOutcome() {
        return outcome;
    }

    public String getInfluence() {
        return influence;
    }
}
