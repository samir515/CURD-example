package com.employeesApiCurd.employeesApiCurd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.employeesApiCurd.employeesApiCurd.model.Employee;
import com.employeesApiCurd.employeesApiCurd.repository.EmployeeRepository;

@Controller
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Endpoint to create a new employee
    @PostMapping("/employees")
    @ResponseBody
    public String createNewEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return "alll ok";
    }

    // Endpoint to get all employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    // Endpoint to get an employee by their ID
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

    // Endpoint to update an employee by their ID
    @PutMapping("/employees/{empid}")
    @ResponseBody
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

    // Endpoint to delete an employee by their ID
    @DeleteMapping("/employees/{empid}")
    @ResponseBody
    public String deleteEmployeeByEmpid(@PathVariable long empid){
        employeeRepository.deleteById(empid);
        return "employee deleted ";
    }

    // Endpoint to delete all employees
    @DeleteMapping("/employees")
    @ResponseBody
    public String deleteAllEmployee(){
        employeeRepository.deleteAll();
        return "employee table data deleted sucessfully";
    }
}
