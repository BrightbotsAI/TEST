package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class Generic {

    public String response_type;
    public String text;
    public ArrayList<Options> options;
    public String title;
    public String header;
    public ArrayList<PrimaryResult> primary_results;
    public ArrayList<AdditionalResult> additional_results;
    public boolean typing;
    public int time;
    public ArrayList<Suggestion> suggestions;
    public String description;
    public String source;
    public ChannelOptions channel_options;

    public String getResponse_type() {
        return response_type;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Options> getOptions() {
        return options;
    }

    public String getTitle() {
        return title;
    }

    public String getHeader() {
        return header;
    }

    public ArrayList<PrimaryResult> getPrimary_results() {
        return primary_results;
    }

    public ArrayList<AdditionalResult> getAdditional_results() {
        return additional_results;
    }

    public boolean isTyping() {
        return typing;
    }

    public int getTime() {
        return time;
    }

    public ArrayList<Suggestion> getSuggestions() {
        return suggestions;
    }

    public String getDescription() {
        return description;
    }

    public String getSource() {
        return source;
    }

    public ChannelOptions getChannel_options() {
        return channel_options;
    }
}
