package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public Employee saveDepartment(@Valid @RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employees")

    public CollectionModel<EntityModel<Employee>> all(){
        return employeeService.all();
    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {
        return employeeService.one(id);
    }

    @PutMapping("/employees/{id}")
    public Employee updateDepartment(@RequestBody Employee employee, @PathVariable("id") Long employeeId){
        return employeeService.updateEmployee(employee, employeeId);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        employeeService.deleteEmployeeById((departmentId));
        return "Deleted Successfully";
    }
}
