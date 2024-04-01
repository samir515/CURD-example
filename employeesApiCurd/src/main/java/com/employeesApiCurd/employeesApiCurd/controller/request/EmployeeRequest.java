package com.employeesApiCurd.employeesApiCurd.controller.request;



import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
   @NotNull
    private String emp_name;
    @NotNull
    private float emp_salary;
    @Min(value = 18, message = "age should be greater than 18")
    @Max(value = 60, message = "age should be less than 60")
    private int emp_age;
    @NotNull
    private String emp_city;


}