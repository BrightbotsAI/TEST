package com.brightbots.dto;

public class RestfulResponse {

    private final short code;
    private final String message;
    private final String exception;
    private final String messageException;
    private final String actions;
    private final String userMessage;

    public RestfulResponse(short code, String message, String exception, String messageException, String actions, String userMessage) {
        this.code = code;
        this.message = message;
        this.exception = exception;
        this.messageException = messageException;
        this.actions = actions;
        this.userMessage = userMessage;
    }

    public short getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getException() {
        return exception;
    }

    public String getMessageException() {
        return messageException;
    }

    public String getActions() {
        return actions;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
