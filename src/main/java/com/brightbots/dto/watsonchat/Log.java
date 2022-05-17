package com.brightbots.dto.watsonchat;

import java.util.Date;

public class Log {

    public String workspace_id;
    public String log_id;
    public Request request;
    public Date response_timestamp;
    public Date request_timestamp;
    public Response response;
    public String language;

    public String getWorkspace_id() {
        return workspace_id;
    }

    public String getLog_id() {
        return log_id;
    }

    public Request getRequest() {
        return request;
    }

    public Date getResponse_timestamp() {
        return response_timestamp;
    }

    public Date getRequest_timestamp() {
        return request_timestamp;
    }

    public Response getResponse() {
        return response;
    }

    public String getLanguage() {
        return language;
    }
}
