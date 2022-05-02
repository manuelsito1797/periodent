package com.project.app.user.model.permission;

import javafx.beans.property.*;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public class FXPermission {

    private final IntegerProperty id;
    private final StringProperty description;
    private final StringProperty key;
    private final BooleanProperty active;

    public FXPermission() {
        this(0, "", "", true);
    }

    public FXPermission(int id, String description, String key, boolean active) {
        this.id = new SimpleIntegerProperty(id);
        this.description = new SimpleStringProperty(description);
        this.key = new SimpleStringProperty(key);
        this.active = new SimpleBooleanProperty(active);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getKey() {
        return key.get();
    }

    public StringProperty keyProperty() {
        return key;
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    @Override
    public String toString() {
        return "FXPermission{" +
                "id=" + id.get() +
                ", description=" + description.get() +
                ", key=" + key.get() +
                ", active=" + active.get() +
                '}';
    }
}
