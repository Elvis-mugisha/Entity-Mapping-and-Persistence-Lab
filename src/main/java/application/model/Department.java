package application.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String building;

    public Department(Long id, List<Nurse> nurses, List<Ward> wards, Doctor director, String building, String name, String code) {
        this.id = id;
        this.nurses = nurses;
        this.wards = wards;
        this.director = director;
        this.building = building;
        this.name = name;
        this.code = code;
    }

    @OneToOne
    @JoinColumn(name = "director_id")
    private Doctor director;

    @OneToMany(mappedBy = "department")
    private List<Ward> wards;

    @OneToMany(mappedBy = "department")
    private List<Nurse> nurses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }

    public Doctor getDirector() {
        return director;
    }

    public void setDirector(Doctor director) {
        this.director = director;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
// Getters and Setters
}
