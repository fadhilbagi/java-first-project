package com.example.demo.service;

import com.example.demo.entity.Department;

import  java.util.List;

public interface DepartmentService {
    //Save
    Department saveDepartment(Department department);
    //Read
    List<Department> fetchDepartmentList();
    //Update
    Department updateDepartment(Department department,Long departmentId);
    //Delete
    void deleteDepartmentById(Long departmentId);
}
