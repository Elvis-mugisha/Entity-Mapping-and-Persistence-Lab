package application.model;


import jakarta.persistence.*;


    @Entity
    public class Nurse extends Employee {
        private String rotation;
        private double salary;

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public String getRotation() {
            return rotation;
        }

        public void setRotation(String rotation) {
            this.rotation = rotation;
        }

        @ManyToOne
        @JoinColumn(name = "department_id")
        private Department department;

        // Getters and Setters
}
