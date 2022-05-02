package com.project.domain.user.model.permission;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public class PermissionResponseModel {

    private int id;
    private String description;
    private String key;
    private boolean active;

    public PermissionResponseModel() {}

    public PermissionResponseModel(int id, String description, String key, boolean active) {
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

    @Override
    public String toString() {
        return "PermissionResponseModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", key='" + key + '\'' +
                ", active=" + active +
                '}';
    }
}
