package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class OutputResponse {
    
    public ArrayList<Generic> generic;
    public ArrayList<String> nodes_visited;
    public Debug debug;
    public ArrayList<LogMessage> log_messages;
    public ArrayList<String> text;
    public ArrayList<NodesVisitedDetail> nodes_visited_details;
    public Spelling spelling;
    public UserDefined user_defined;

    public ArrayList<Generic> getGeneric() {
        return generic;
    }

    public ArrayList<String> getNodes_visited() {
        return nodes_visited;
    }

    public Debug getDebug() {
        return debug;
    }

    public ArrayList<LogMessage> getLog_messages() {
        return log_messages;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public ArrayList<NodesVisitedDetail> getNodes_visited_details() {
        return nodes_visited_details;
    }

    public Spelling getSpelling() {
        return spelling;
    }

    public UserDefined getUser_defined() {
        return user_defined;
    }
}
