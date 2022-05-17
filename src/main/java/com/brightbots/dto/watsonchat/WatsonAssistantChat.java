package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class WatsonAssistantChat{

    public ArrayList<Log> logs;
    public Pagination pagination;

    public ArrayList<Log> getLogs() {
        return logs;
    }
    public Pagination getPagination() {
        return pagination;
    }
}