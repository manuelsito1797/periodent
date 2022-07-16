package com.project.domain.specialist.model.specialist;

import java.sql.Date;
import java.util.List;

/**
 * @author dhelarius 7/7/2022
 * periodent
 */
public class SpecialistResponseModel implements Specialist {

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
    private List<Specialty> specialties;

    public SpecialistResponseModel() {}

    public SpecialistResponseModel(int id, String name, String lastname, Date birthday, String dni, String phone, String address,
                                   String email, int createdBy, String createdByName, boolean active, List<Specialty> specialties) {
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
        this.specialties = specialties;
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

    public String getCreatedByName() {
        return createdByName;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    @Override
    public String toString() {
        return "SpecialistResponseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", dni='" + dni + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", createdBy=" + createdBy +
                ", createdByName='" + createdByName + '\'' +
                ", active=" + active +
                ", specialties=" + specialties +
                '}';
    }
}
