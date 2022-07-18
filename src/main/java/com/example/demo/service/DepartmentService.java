package com.example.demo.service;

import com.example.demo.entity.Department;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface DepartmentService {
    //Save
    Department saveDepartment(Department department);
    //Read
    CollectionModel<EntityModel<Department>> all();
    EntityModel<Department> one(Long id);
    //Update
    Department updateDepartment(Department department,Long departmentId);
    //Delete
    void deleteDepartmentById(Long departmentId);
    Long getId(Long departmentId);
}
