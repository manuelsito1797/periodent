package com.project.domain.user.model.permission;

/**
 * @author dhelarius 17/4/2022
 * periodent
 */
public class UserPermissionDsRequestModel {

    private int id;
    private String description;
    private String key;
    private boolean assigned;

    public UserPermissionDsRequestModel(int id, String description, String key, boolean assigned) {
        this.id = id;
        this.description = description;
        this.key = key;
        this.assigned = assigned;
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

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
