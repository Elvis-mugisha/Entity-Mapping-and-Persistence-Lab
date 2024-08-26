package application.model;

import jakarta.persistence.*;

@Entity
public class Doctor extends Employee {
    private String specialty;

    // Getters and Setters

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
