package com.project.app.user.model;

import com.project.app.user.model.permission.FXUserPermission;
import com.project.domain.user.model.User;
import com.project.domain.user.model.permission.UserPermission;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dhelarius 15/4/2022
 * periodent
 */
public class FXUser {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty lastname;
    private final StringProperty dni;
    private final StringProperty phone;
    private final StringProperty email;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty createdBy;
    private final Property<Timestamp> creationDate;
    private final BooleanProperty status;
    private final BooleanProperty selected;
    private ObservableList<FXUserPermission> permissions;

    public FXUser() {
        this(0, null, null, null, null,
                null, null, null, null, null, false,
                new ArrayList<>());
    }

    public FXUser(int id, String name, String lastname, String dni, String phone, String email,
                  String username, String password, String createdBy, Timestamp creationDate , boolean status,
                  List<UserPermission> permissions) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.lastname = new SimpleStringProperty(lastname);
        this.dni = new SimpleStringProperty(dni);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.creationDate = new SimpleObjectProperty<>(creationDate);
        this.status = new SimpleBooleanProperty(status);
        this.selected = new SimpleBooleanProperty(false);
        initPermissions(permissions);
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getCreatedBy() {
        return createdBy.get();
    }

    public StringProperty createdByProperty() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy.set(createdBy);
    }

    public Timestamp getCreationDate() {
        return creationDate.getValue();
    }

    public Property<Timestamp> creationDateProperty() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate.setValue(creationDate);
    }

    public boolean isStatus() {
        return status.get();
    }

    public BooleanProperty statusProperty() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }

    public void setPermissions(List<UserPermission> permissions) {
        initPermissions(permissions);
    }

    public ObservableList<FXUserPermission> getPermissions() {
        return permissions;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    private void initPermissions(List<UserPermission> permissions) {
        var fxPermissions = new ArrayList<FXUserPermission>();
        for(var permission : permissions) {
            fxPermissions.add(new FXUserPermission(permission.getId(), permission.getDescription(),
                    permission.getKey(), permission.isAssigned()));
        }
        ObservableList<FXUserPermission> observableList = FXCollections.observableList(fxPermissions);
        this.permissions = new SimpleListProperty<>(observableList);
    }

    @Override
    public String toString() {
        return "FXUser{" +
                "id=" + id.get() +
                ", name=" + name.get() +
                ", lastname=" + lastname.get() +
                ", dni=" + dni.get() +
                ", phone=" + phone.get() +
                ", email=" + email.get() +
                ", username=" + username.get() +
                ", createdBy=" + createdBy.get() +
                ", creationDate=" + creationDate.getValue() +
                ", status=" + status.get() +
                '}';
    }
}
