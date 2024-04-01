package com.employeesApiCurd.employeesApiCurd.controller;

import com.employeesApiCurd.employeesApiCurd.controller.request.EmployeeRequest;
import com.employeesApiCurd.employeesApiCurd.model.Employee;
import com.employeesApiCurd.employeesApiCurd.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> createNewEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        employeeService.addEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployeeById(@PathVariable("id") long id, @RequestBody Employee employee) {
        try {
            employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok("Employee updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeByEmpid(@PathVariable("id") long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllEmployee() {
        employeeService.deleteAllEmployees();
        return ResponseEntity.ok("All employees deleted successfully");
    }
}
