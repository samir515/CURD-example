package com.employeesApiCurd.employeesApiCurd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeesApiCurd.employeesApiCurd.model.Employee;
import com.employeesApiCurd.employeesApiCurd.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    
    @Autowired
    EmployeeRepository employeeRepository;
//inserting value using postman
    @PostMapping("/employees")
    public String createNewEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return "alll ok";
    }
//if you want to get all the elements of the table
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
//if you want to get element by their unique id
    @GetMapping("/employees/{empid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid){
    Optional<Employee> emp = employeeRepository.findById(empid);
    if (emp.isPresent()){
        return new ResponseEntity<>(emp.get(),HttpStatus.FOUND);
    }
    else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }
//for editing the table i.e. update by id
@PutMapping("/employees/{empid}")
public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee) {
    Optional<Employee> emp = employeeRepository.findById(empid);
    if (emp.isPresent()) {
        Employee existEmp = emp.get();
        existEmp.setEmp_age(employee.getEmp_age());
        existEmp.setEmp_city(employee.getEmp_city());
        existEmp.setEmp_name(employee.getEmp_name());
        existEmp.setEmp_salary(employee.getEmp_salary());
        employeeRepository.save(existEmp);
        return "Employee Details against Id " + empid + " updated";
    } else {
        return "Employee Details do not exist for ID " + empid;
    }
}
//delete certain entity using their id
@DeleteMapping("/employees/{empid}")
public String deleteEmployeeByEmpid(@PathVariable long empid){
    employeeRepository.deleteById(empid);
    return"employee deleted ";
}
//delete all employees i.e all from the table
@DeleteMapping("/employees")
public String deleteAllEmployee(){
    employeeRepository.deleteAll();
    return"employee table data deleted sucessfully";
}
    
}


