package com.employeesApiCurd.employeesApiCurd.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empid;
    
    @Column(name = "emp_name")
    private String emp_name;
    
    @Column(name = "emp_salary")
    private float emp_salary;
    
    @Column(name = "emp_age")
    private int emp_age;
    
    @Column(name  = "emp_city")
    private String emp_city;

}
