package com.project.domain.specialist.model.specialist;

/**
 * @author dhelarius 7/7/2022
 * periodent
 */
public class Specialty {

    private int id;
    private String description;
    private int assignedBy;

    public Specialty() {}

    public Specialty(int id, String description, int assignedBy) {
        this.id = id;
        this.description = description;
        this.assignedBy = assignedBy;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getAssignedBy() {
        return assignedBy;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", assignedBy=" + assignedBy +
                '}';
    }
}
