package com.example.demo.helper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.demo.controller.DepartmentController;
import com.example.demo.entity.Department;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DepartmentModelAssembler implements RepresentationModelAssembler<Department, EntityModel<Department>> {

    @Override
    public EntityModel<Department> toModel(Department department) {
        System.out.println(department);
        return EntityModel.of(department, //
                linkTo(methodOn(DepartmentController.class).one(department.getId())).withSelfRel(),
                linkTo(methodOn(DepartmentController.class).all()).withRel("departments"));
    }
}
