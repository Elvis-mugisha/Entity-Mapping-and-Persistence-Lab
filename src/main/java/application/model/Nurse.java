package application.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Nurse extends Employee {
    private String rotation;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Constructor with parameters
    public Nurse(String phoneNumber, String address, String firstName, String surname, String employeeNumber, Long id, Department department, double salary, String rotation) {
        super(phoneNumber, address, firstName, surname, employeeNumber, id);
        this.department = department;
        this.salary = salary;
        this.rotation = rotation;
    }
}
