package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class AdditionalResult {
    public Highlight highlight;
    public ResultMetadata result_metadata;
    public ArrayList<Answer> answers;
    public String id;
    public String body;
    public String title;
    public String url;

    public Highlight getHighlight() {
        return highlight;
    }

    public ResultMetadata getResult_metadata() {
        return result_metadata;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
