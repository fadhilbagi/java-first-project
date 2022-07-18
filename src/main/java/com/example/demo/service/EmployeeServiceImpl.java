package com.example.demo.service;

import com.example.demo.controller.EmployeeController;
import com.example.demo.entity.Employee;
import com.example.demo.helper.EmployeeModelAssembler;
import com.example.demo.helper.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeModelAssembler assembler;


    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save((employee));
    }


    @Override
    public CollectionModel<EntityModel<Employee>> all(){
        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
                .map(department -> EntityModel.of(department,
                        linkTo(methodOn(EmployeeController.class).one(department.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    };

    @Override
    public EntityModel<Employee> one(Long id) {
        Employee employee = employeeRepository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return assembler.toModel(employee);
    }


    @Override
    public Long getEmployeeId(Long departmentId) {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee, Long departmentId){
        Employee depDB = employeeRepository.findById(departmentId).get();

        if (Objects.nonNull(employee.getName()) && !"".equalsIgnoreCase(employee.getName())) {
            depDB.setName(employee.getName());
        }

        if(Objects.nonNull(employee.getRole())){
            depDB.setRole(employee.getRole());
        }



        return employeeRepository.save(depDB);
    }

    @Override
    public void deleteEmployeeById(Long departmentId)
    {
        employeeRepository.deleteById(departmentId);
    }

    @Override
    public Long getId(Long departmentId){ return departmentId;};

    @Override
    public int hashCode(Long id, String firstName, String lastName, String role) {
        return Objects.hash(id, firstName, lastName, role);
    }

    @Override
    public String toString(Long id, String firstName, String lastName, String role) {
        return "Employee{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
                + '\'' + ", role='" + role + '\'' + '}';
    }
}
