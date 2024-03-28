package com.employeesApiCurd.employeesApiCurd.service;

import com.employeesApiCurd.employeesApiCurd.model.Employee;
import com.employeesApiCurd.employeesApiCurd.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Getting all employees");
        List<Employee> employees = employeeRepository.findAll();
        logger.info("Retrieved {} employees", employees.size());
        return employees;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        logger.info("Getting employee by ID: {}", id);
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            logger.info("Employee found with ID: {}", id);
            return employeeOptional.get();
        } else {
            logger.warn("Employee not found with ID: {}", id);
            return null;
        }
    }

    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        logger.info("Adding new employee: {}", employee);
        employeeRepository.save(employee);
        logger.info("Employee added successfully");
    }

    @Override
    @Transactional
    public void updateEmployee(Long id, Employee updatedEmployee) {
        logger.info("Updating employee with ID: {}", id);
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();
            existingEmployee.setEmp_name(updatedEmployee.getEmp_name());
            existingEmployee.setEmp_salary(updatedEmployee.getEmp_salary());
            existingEmployee.setEmp_age(updatedEmployee.getEmp_age());
            existingEmployee.setEmp_city(updatedEmployee.getEmp_city());
            employeeRepository.save(existingEmployee);
            logger.info("Employee updated successfully");
        } else {
            logger.warn("Employee not found with ID: {}", id);
            throw new IllegalArgumentException("Employee not found with ID: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with ID: {}", id);
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            logger.info("Employee deleted successfully");
        } else {
            logger.warn("Employee not found with ID: {}", id);
            throw new IllegalArgumentException("Employee not found with ID: " + id);
        }
    }
}
