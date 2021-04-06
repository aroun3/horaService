package com.visitor.controller;

import com.visitor.entities.Role;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.payload.response.EmployeeResponse;
import com.visitor.services.EmployeeService;
import com.visitor.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employee")
    public List<EmployeeResponse> getEmployeAndDepaetment(){
       return employeeService.getListEmployeeAndDepartment();
    }


}
