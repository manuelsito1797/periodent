package com.project.domain.user.model.permission;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public class PermissionDsRequestModel {

    private int id;
    private String description;
    private String key;
    private boolean active;

    public PermissionDsRequestModel() {}

    public PermissionDsRequestModel(int id, String description, String key, boolean active) {
        this.id = id;
        this.description = description;
        this.key = key;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
