package com.project.app.user.model.permission;

import javafx.beans.property.*;

/**
 * @author dhelarius 23/4/2022
 * periodent
 */
public class FXUserPermission {

    private final IntegerProperty id;
    private final StringProperty description;
    private final StringProperty key;
    private final BooleanProperty assigned;

    public FXUserPermission() {
        this(0, null, null, false);
    }

    public FXUserPermission(int id, String description, String key, boolean assigned) {
        this.id = new SimpleIntegerProperty(id);
        this.description = new SimpleStringProperty(description);
        this.key = new SimpleStringProperty(key);
        this.assigned = new SimpleBooleanProperty(assigned);
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

    public boolean isAssigned() {
        return assigned.get();
    }

    public BooleanProperty assignedProperty() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned.set(assigned);
    }

    @Override
    public String toString() {
        return "FXPermission{" +
                "id=" + id +
                ", description=" + description +
                ", key=" + key +
                ", assigned=" + assigned +
                '}';
    }
}
