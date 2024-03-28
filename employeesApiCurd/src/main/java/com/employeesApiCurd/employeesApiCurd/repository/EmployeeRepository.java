package com.employeesApiCurd.employeesApiCurd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employeesApiCurd.employeesApiCurd.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long > {

}
