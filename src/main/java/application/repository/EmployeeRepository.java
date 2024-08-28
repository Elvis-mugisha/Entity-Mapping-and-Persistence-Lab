package application.repository;

import application.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find an Employee by their Employee Number
    Optional<Employee> findByEmployeeNumber(String employeeNumber);

    // Find Employees by Surname
    List<Employee> findBySurname(String surname);

    // Find Employees by First Name
    List<Employee> findByFirstName(String firstName);

    // Find Employees by Address
    List<Employee> findByAddress(String address);

    // Find Employees by Phone Number
    List<Employee> findByPhoneNumber(String phoneNumber);

    Employee save(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
