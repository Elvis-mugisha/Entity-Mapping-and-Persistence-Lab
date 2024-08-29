package application.service;

import application.model.Employee;
import application.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    // 1. Declarative transaction example with caching and default settings
    @Transactional
    @Cacheable(value = "employees", unless = "#result.isEmpty()")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 2. Declarative transaction with specific isolation and propagation
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CachePut(value = "employee", key = "#employee.id")
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // 3. Declarative transaction for fetching employee by ID with caching
    @Transactional(readOnly = true)
    @Cacheable(value = "employee", key = "#id")
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // 4. Declarative transaction for updating an employee's information
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @CachePut(value = "employee", key = "#id")
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setSurname(employeeDetails.getSurname());
            employee.setAddress(employeeDetails.getAddress());
            employee.setPhoneNumber(employeeDetails.getPhoneNumber());
            employee.setEmployeeNumber(employeeDetails.getEmployeeNumber());
            return employeeRepository.save(employee);
        } else {
            throw new RuntimeException("Employee not found with ID " + id);
        }
    }

    // 5. Programmatic transaction for deleting an employee
    @CacheEvict(value = "employee", key = "#id")
    public boolean deleteEmployee(Long id) {
        // Define transaction settings
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("DeleteEmployeeTransaction");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        // Start the transaction
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // Check if the employee exists
            Optional<Employee> employeeOptional = employeeRepository.findById(id);

            if (employeeOptional.isPresent()) {
                // Delete the employee
                employeeRepository.deleteById(id);

                // Commit the transaction
                transactionManager.commit(status);
            } else {
                // Rollback if the employee does not exist
                transactionManager.rollback(status);
                throw new RuntimeException("Employee not found with ID " + id);
            }

        } catch (Exception e) {
            // Rollback the transaction in case of an exception
            transactionManager.rollback(status);
            throw e;
        }
        return false;
    }
}
