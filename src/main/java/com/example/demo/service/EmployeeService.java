package com.example.demo.service;

import com.example.demo.entity.Employee;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;


public interface EmployeeService {

    Employee saveEmployee(Employee department);
    //Read
    CollectionModel<EntityModel<Employee>> all();
    EntityModel<Employee> one(Long id);
    //Update
    Employee updateEmployee(Employee department,Long departmentId);
    //Delete
    void deleteEmployeeById(Long departmentId);
    Long getEmployeeId(Long departmentId);

    Long getId(Long departmentId);

    int hashCode(Long id, String firstName, String lastName, String role);

    String toString(Long id, String firstName, String lastName, String role);
}
