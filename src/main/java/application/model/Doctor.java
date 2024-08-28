package application.model;

import jakarta.persistence.Entity;

@Entity
public class Doctor extends Employee {
    private String specialty;

    // Default constructor
    public Doctor() {
        super();
    }

    // Getters and Setters
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
