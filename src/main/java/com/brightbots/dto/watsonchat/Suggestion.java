package com.brightbots.dto.watsonchat;

public class Suggestion{

    public OutputSuggestions output;
    public String label;
    public String source_dialog_node;
    public Value value;
    public String dialog_node;

    public OutputSuggestions getOutput() {
        return output;
    }

    public String getLabel() {
        return label;
    }

    public String getSource_dialog_node() {
        return source_dialog_node;
    }

    public Value getValue() {
        return value;
    }

    public String getDialog_node() {
        return dialog_node;
    }
}
