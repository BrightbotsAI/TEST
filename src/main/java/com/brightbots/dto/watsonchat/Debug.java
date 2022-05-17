package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class Debug {

    public AutoLearn auto_learn;
    public ArrayList<LogMessage> log_messages;
    public boolean branch_exited;
    public String branch_exited_reason;
    public ArrayList<NodesVisited> nodes_visited;

    public AutoLearn getAuto_learn() {
        return auto_learn;
    }

    public ArrayList<LogMessage> getLog_messages() {
        return log_messages;
    }

    public boolean isBranch_exited() {
        return branch_exited;
    }

    public String getBranch_exited_reason() {
        return branch_exited_reason;
    }

    public ArrayList<NodesVisited> getNodes_visited() {
        return nodes_visited;
    }
}
