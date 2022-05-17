package com.brightbots.dto.watsonchat;

public class Options {

    public boolean alternate_responses;
    public boolean debug;
    public Disambiguation disambiguation;
    public boolean suggestion_only;
    public boolean return_context;
    public boolean export;
    public String label;
    public Value value;

    public boolean isAlternate_responses() {
        return alternate_responses;
    }

    public boolean isDebug() {
        return debug;
    }

    public Disambiguation getDisambiguation() {
        return disambiguation;
    }

    public boolean isSuggestion_only() {
        return suggestion_only;
    }

    public boolean isReturn_context() {
        return return_context;
    }

    public boolean isExport() {
        return export;
    }

    public String getLabel() {
        return label;
    }

    public Value getValue() {
        return value;
    }
}
