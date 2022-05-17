package com.brightbots.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InsightsDTO {

    private String name;
    private Long value;
    private Map<String, Long> finalHashMap;
    private List<?> finalList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Map<String, Long> getFinalHashMap() {
        return finalHashMap;
    }

    public void setFinalHashMap(Map<String, Long> finalHashMap) {
        this.finalHashMap = finalHashMap;
    }

    public List<?> getFinalList() {
        return finalList;
    }

    public void setFinalList(List<?> finalList) {
        this.finalList = finalList;
    }
}
