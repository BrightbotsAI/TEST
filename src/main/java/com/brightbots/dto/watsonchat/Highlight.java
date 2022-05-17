package com.brightbots.dto.watsonchat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Highlight {

    @JsonProperty("extracted_metadata.filename")
    public ArrayList<String> extractedMetadataFilename;
    @JsonProperty("enriched_text.entities.mentions.text")
    public ArrayList<String> enrichedText;
    @JsonProperty("metadata.source.shared_link.url")
    public ArrayList<String> metadataSourceSharedLinkUrl;
    public ArrayList<Object> text;
    public ArrayList<Object> body;
    public ArrayList<String> title;
    public ArrayList<String> url;

    public ArrayList<String> getExtractedMetadataFilename() {
        return extractedMetadataFilename;
    }

    public ArrayList<String> getEnrichedText() {
        return enrichedText;
    }

    public ArrayList<String> getMetadataSourceSharedLinkUrl() {
        return metadataSourceSharedLinkUrl;
    }

    public ArrayList<Object> getText() {
        return text;
    }

    public ArrayList<Object> getBody() {
        return body;
    }

    public ArrayList<String> getTitle() {
        return title;
    }

    public ArrayList<String> getUrl() {
        return url;
    }
}

