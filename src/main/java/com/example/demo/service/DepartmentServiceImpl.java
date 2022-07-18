package com.example.demo.service;

import com.example.demo.controller.DepartmentController;
import com.example.demo.entity.Department;
import com.example.demo.helper.DepartmentModelAssembler;
import com.example.demo.helper.EmployeeNotFoundException;
import com.example.demo.repository.DepartmentRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private  DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentModelAssembler assembler;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save((department));
    }

    @Override
    public CollectionModel<EntityModel<Department>> all(){
        List<EntityModel<Department>> departments = departmentRepository.findAll().stream()
                .map(department -> EntityModel.of(department,
                        linkTo(methodOn(DepartmentController.class).one(department.getId())).withSelfRel(),
                        linkTo(methodOn(DepartmentController.class).all()).withRel("departments")))
                .collect(Collectors.toList());

        return CollectionModel.of(departments, linkTo(methodOn(DepartmentController.class).all()).withSelfRel());
    };

    @Override
    public EntityModel<Department> one(Long id) {
        Department department = departmentRepository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return assembler.toModel(department);
    }

    @Override
    public Department updateDepartment(Department department,Long departmentId){
        Department depDB = departmentRepository.findById(departmentId).get();

        if (Objects.nonNull(department.getName()) && !"".equalsIgnoreCase(department.getName())) {
            depDB.setName(department.getName());
        }

        if(Objects.nonNull(department.getAddress())){
            depDB.setAddress(department.getAddress());
        }

        if (Objects.nonNull(department.getCode())
                && !"".equalsIgnoreCase(
                department.getCode())) {
            depDB.setCode(
                    department.getCode());
        }

        return departmentRepository.save(depDB);
    }

    @Override
    public void deleteDepartmentById(Long departmentId)
    {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Long getId(Long departmentId){ return departmentId;};
}
