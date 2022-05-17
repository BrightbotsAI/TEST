
package com.brightbots.dto.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PathCollection {

    @JsonProperty("total_count")
    private int totalCount;
    private List<Entry> entries;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
