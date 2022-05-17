package com.brightbots.dto.watsonchat;

public class NodesVisitedDetail {
    public String visit_reason;
    public String conditions;
    public boolean leaf;
    public String title;
    public String type;
    public String dialog_node;
    public NextStep next_step;

    public String getVisit_reason() {
        return visit_reason;
    }

    public String getConditions() {
        return conditions;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDialog_node() {
        return dialog_node;
    }

    public NextStep getNext_step() {
        return next_step;
    }
}
