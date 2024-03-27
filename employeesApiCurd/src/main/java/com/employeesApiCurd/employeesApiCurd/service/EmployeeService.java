package com.employeesApiCurd.employeesApiCurd.service;

import com.employeesApiCurd.employeesApiCurd.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    void addEmployee(Employee employee);
    void updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}
