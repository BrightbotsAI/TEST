package com.brightbots.dto;

public class RequestSharedLink {

    private String itemId;
    private String resourceType;

    public RequestSharedLink(String itemId, String resourceType) {
        this.itemId = itemId;
        this.resourceType = resourceType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
