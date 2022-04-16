package com.project.app.user.model;

import javafx.beans.property.*;

import java.sql.Timestamp;

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
    private final StringProperty createdBy;
    private final Property<Timestamp> creationDate;
    private final BooleanProperty status;

    public FXUser() {
        this(0, null, null, null, null,
                null, null, null, null, false);
    }

    public FXUser(int id, String name, String lastname, String dni, String phone, String email,
                  String username, String createdBy, Timestamp creationDate ,boolean status) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.lastname = new SimpleStringProperty(lastname);
        this.dni = new SimpleStringProperty(dni);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.creationDate = new SimpleObjectProperty<>(creationDate);
        this.status = new SimpleBooleanProperty(status);
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
}
