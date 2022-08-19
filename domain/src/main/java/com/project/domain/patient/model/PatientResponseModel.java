package com.project.domain.patient.model;

import java.sql.Date;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public class PatientResponseModel {

    private int id;
    private String name;
    private String lastname;
    private Date birthday;
    private String dni;
    private String phone;
    private String address;
    private String email;
    private int createdBy;
    private String createdByName;
    private boolean active;

    public PatientResponseModel() {}

    public PatientResponseModel(int id, String name, String lastname, Date birthday, String dni, String phone,
                         String address, String email, int createdBy, String createdByName, boolean active) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.dni = dni;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.createdBy = createdBy;
        this.createdByName = createdByName;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getDni() {
        return dni;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "PatientResponseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", dni='" + dni + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", createdBy=" + createdBy +
                ", active=" + active +
                '}';
    }
}
