package application.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wardNumber;
    private int numberOfBeds;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Ward(Long id, List<Patient> patients, Nurse supervisor, Department department, int numberOfBeds, String wardNumber) {
        this.id = id;
        this.patients = patients;
        this.supervisor = supervisor;
        this.department = department;
        this.numberOfBeds = numberOfBeds;
        this.wardNumber = wardNumber;
    }

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    private Nurse supervisor;

    @OneToMany(mappedBy = "ward")
    private List<Patient> patients;

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public Nurse getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Nurse supervisor) {
        this.supervisor = supervisor;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }
// Getters and Setters
}

