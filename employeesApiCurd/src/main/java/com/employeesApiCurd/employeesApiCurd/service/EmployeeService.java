package com.employeesApiCurd.employeesApiCurd.service;

import com.employeesApiCurd.employeesApiCurd.controller.request.EmployeeRequest;
import com.employeesApiCurd.employeesApiCurd.model.Employee;
import jakarta.validation.Valid;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    void addEmployee(@Valid EmployeeRequest employee);
    void updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    void deleteAllEmployees();
}
