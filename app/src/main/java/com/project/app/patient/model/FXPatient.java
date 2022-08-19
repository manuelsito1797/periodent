package com.project.app.patient.model;

import javafx.beans.property.*;
import javafx.util.StringConverter;

import java.time.LocalDate;

/**
 * @author dhelarius 21/7/2022
 * periodent
 */
public class FXPatient {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty lastname;
    private final Property<StringConverter<LocalDate>> birthday;
    private final StringProperty dni;
    private final StringProperty phone;
    private final StringProperty address;
    private final StringProperty email;
    private final BooleanProperty active;

    public FXPatient() {
        this(0, null, null, null, null, null, null, null, false);
    }

    public FXPatient(int id, String name, String lastname, StringConverter<LocalDate> birthday,
                     String dni, String phone, String address, String email, boolean active) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.lastname = new SimpleStringProperty(lastname);
        this.birthday = new SimpleObjectProperty<>(birthday);
        this.dni = new SimpleStringProperty(dni);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
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

    public StringConverter<LocalDate> getBirthday() {
        return birthday.getValue();
    }

    public Property<StringConverter<LocalDate>> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(StringConverter<LocalDate> birthday) {
        this.birthday.setValue(birthday);
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

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
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
        return "FXPatient{" +
                "id=" + id.get() +
                ", name=" + name.get() +
                ", lastname=" + lastname.get() +
                ", birthday=" + birthday.getValue() +
                ", dni=" + dni.get() +
                ", phone=" + phone.get() +
                ", address=" + address.get() +
                ", email=" + email.get() +
                ", active=" + active.get() +
                '}';
    }
}
