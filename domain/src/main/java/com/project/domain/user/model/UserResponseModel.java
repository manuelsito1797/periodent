package com.project.domain.user.model;

import java.sql.Timestamp;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class UserResponseModel {

    private final int id;
    private final String name;
    private final String lastname;
    private final String dni;
    private final String phone;
    private final String email;
    private final String username;
    private final String createdBy;
    private final Timestamp creationDate;
    private final boolean status;

    public UserResponseModel(int id, String name, String lastname, String dni, String phone,
                             String email, String username, String createdBy, Timestamp creationDate,
                             boolean status) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.status = status;
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

    public String getDni() {
        return dni;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public boolean isStatus() {
        return status;
    }

    public String toString() {
        return "UserResponseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dni='" + dni + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", status=" + status +
                '}';
    }
}
