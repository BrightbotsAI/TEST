package com.brightbots.dto.watsonchat;

public class LogMessage {

    public String code;
    public String level;
    public Source source;
    public String message;
    public String node_title;
    public String node_id;

    public String getCode() {
        return code;
    }

    public String getLevel() {
        return level;
    }

    public Source getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    public String getNode_title() {
        return node_title;
    }

    public String getNode_id() {
        return node_id;
    }
}