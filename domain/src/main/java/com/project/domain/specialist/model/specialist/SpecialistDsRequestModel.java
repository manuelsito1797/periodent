package com.project.domain.specialist.model.specialist;

import java.sql.Date;

/**
 * @author dhelarius 7/7/2022
 * periodent
 */
public class SpecialistDsRequestModel implements Specialist {

    private int id;
    private String name;
    private String lastname;
    private Date birthday;
    private String dni;
    private String phone;
    private String address;
    private String email;
    private int createdBy;
    private boolean active;

    public SpecialistDsRequestModel() {}

    public SpecialistDsRequestModel(int id, String name, String lastname, Date birthday, String dni,
                                    String phone, String address, String email, int createdBy, boolean active) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.dni = dni;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.createdBy = createdBy;
        this.active = active;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String getDni() {
        return dni;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getCreatedBy() {
        return createdBy;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "SpecialistDsRequestModel{" +
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
