package com.brightbots.dto.watsonchat;

public class ResultMetadata {

    public String collection_id;
    public String document_retrieval_source;
    public double confidence;

    public String getCollection_id() {
        return collection_id;
    }

    public String getDocument_retrieval_source() {
        return document_retrieval_source;
    }

    public double getConfidence() {
        return confidence;
    }
}
