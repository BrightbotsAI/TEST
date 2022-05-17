
package com.brightbots.dto.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class RequestWebwook {

    private String type;
    private String id;
    @JsonProperty("created_at")
    private Date createdAt;
    private String trigger;
    private Webhook webhook;
    @JsonProperty("created_by")
    private CreatedBy createdBy;
    private Source source;
    @JsonProperty("additional_info")
    private List<Object> additionalInfo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public Webhook getWebhook() {
        return webhook;
    }

    public void setWebhook(Webhook webhook) {
        this.webhook = webhook;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public List<Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
