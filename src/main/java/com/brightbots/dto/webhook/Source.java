
package com.brightbots.dto.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Source {

    private String id;
    private String type;
    @JsonProperty("file_version")
    private FileVersion fileVersion;
    @JsonProperty("sequence_id")
    private String sequenceId;
    private String etag;
    private String sha1;
    private String name;
    private String description;
    private int size;
    @JsonProperty("path_collection")
    private PathCollection pathCollection;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("modified_at")
    private Date modifiedAt;
    @JsonProperty("trashed_at")
    private Date trashedAt;
    @JsonProperty("purged_at")
    private Date purgedAt;
    @JsonProperty("content_created_at")
    private Date contentCreatedAt;
    @JsonProperty("content_modified_at")
    private Date contentModifiedAt;
    @JsonProperty("created_by")
    private CreatedBySource createdBy;
    @JsonProperty("modified_by")
    private ModifiedBy modifiedBy;
    @JsonProperty("owned_by")
    private OwnedBy ownedBy;
    @JsonProperty("shared_link")
    private String sharedLink;
    private Parent parent;
    @JsonProperty("item_status")
    private String itemStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FileVersion getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(FileVersion fileVersion) {
        this.fileVersion = fileVersion;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public PathCollection getPathCollection() {
        return pathCollection;
    }

    public void setPathCollection(PathCollection pathCollection) {
        this.pathCollection = pathCollection;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Date getTrashedAt() {
        return trashedAt;
    }

    public void setTrashedAt(Date trashedAt) {
        this.trashedAt = trashedAt;
    }

    public Date getPurgedAt() {
        return purgedAt;
    }

    public void setPurgedAt(Date purgedAt) {
        this.purgedAt = purgedAt;
    }

    public Date getContentCreatedAt() {
        return contentCreatedAt;
    }

    public void setContentCreatedAt(Date contentCreatedAt) {
        this.contentCreatedAt = contentCreatedAt;
    }

    public Date getContentModifiedAt() {
        return contentModifiedAt;
    }

    public void setContentModifiedAt(Date contentModifiedAt) {
        this.contentModifiedAt = contentModifiedAt;
    }

    public CreatedBySource getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBySource createdBy) {
        this.createdBy = createdBy;
    }

    public ModifiedBy getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(ModifiedBy modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public OwnedBy getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(OwnedBy ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getSharedLink() {
        return sharedLink;
    }

    public void setSharedLink(String sharedLink) {
        this.sharedLink = sharedLink;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }
}
