package com.project.domain.user.model;

import com.project.domain.user.model.permission.UserPermission;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class CommonUser implements User {

    private int id;
    private String name;
    private String lastname;
    private String dni;
    private String phone;
    private String email;
    private String username;
    private String password;
    private int createdBy;
    private Timestamp creationDate;
    private boolean status;
    private List<UserPermission> permissions;

    public CommonUser() {}

    public CommonUser(int id, String name, String lastname, String dni, String phone,
                      String email, String username, String password, int createdBy,
                      Timestamp creationDate, boolean status) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setPermissions(List<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public List<UserPermission> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "CommonUser{" +
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
