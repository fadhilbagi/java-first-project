package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {
    @Autowired private DepartmentService departmentService;

    @PostMapping("/departments")

    public Department saveDepartment(@Valid @RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")

    public CollectionModel<EntityModel<Department>> all(){
        return departmentService.all();
    }

    @GetMapping("/departments/{id}")
    public EntityModel<Department> one(@PathVariable Long id) {
        return departmentService.one(id);
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@RequestBody Department department, @PathVariable("id") Long departmentId){
        return departmentService.updateDepartment(department, departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById((departmentId));
        return "Deleted Successfully";
    }
}
