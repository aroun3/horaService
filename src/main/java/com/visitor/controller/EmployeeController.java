package com.visitor.controller;

import com.visitor.entities.Employee;
import com.visitor.entities.Role;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.payload.response.EmployeeResponse;
import com.visitor.services.EmployeeService;
import com.visitor.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/listEmployee")
    public List<Employee> getAllEmployee(){
        return employeeService.findAll();
    }



    @GetMapping("/employee")
    public List<EmployeeResponse> getEmployeAndDepaetment(){
       return employeeService.getListEmployeeAndDepartment();
    }

    @GetMapping("/getEmployeeByFirstNameOrLastName")
    public List<EmployeeResponse> findByFirstNameOrLastName(@RequestParam(value ="firstName")  String firstName, @RequestParam(value="lastName") String lastName){
        return employeeService.findByFirstNameOrLastName(firstName, lastName);
    }

}
