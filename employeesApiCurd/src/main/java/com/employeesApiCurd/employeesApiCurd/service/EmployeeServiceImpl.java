package com.employeesApiCurd.employeesApiCurd.service;

import com.employeesApiCurd.employeesApiCurd.model.Employee;
import com.employeesApiCurd.employeesApiCurd.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            // Update employee properties
            existingEmployee.setEmp_name(employee.getEmp_name());
            existingEmployee.setEmp_salary(employee.getEmp_salary());
            existingEmployee.setEmp_age(employee.getEmp_age());
            existingEmployee.setEmp_city(employee.getEmp_city());
            employeeRepository.save(existingEmployee);
        }
        
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
