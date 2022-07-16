package com.project.domain.specialist.model.specialty;

/**
 * @author dhelarius 7/7/2022
 * periodent
 */
public class CommonSpecialty implements Specialty {

    private int id;
    private String description;
    private boolean active;

    public CommonSpecialty () {}

    public CommonSpecialty (int id, String description, boolean active) {
        this.id = id;
        this.description = description;
        this.active = active;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "CommonSpecialist{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
