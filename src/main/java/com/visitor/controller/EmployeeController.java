package com.visitor.controller;

import com.visitor.payload.response.EmployeeResponse;
import com.visitor.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/listEmployee")
    public List<EmployeeResponse> getAllEmployee(){
        //return employeeService.findAll();
        return  employeeService.getAllEmployee();
    }

    @GetMapping("/getEmployeeByCode/{code}")
    public List<EmployeeResponse> getEmployeeByCode(@PathVariable String code){
        return  employeeService.getEmployeeByCode(code);
    }

    @GetMapping("/searchEmployee/{motcle}")
    public List<EmployeeResponse> searchEmployee(@PathVariable String motcle){
        return  employeeService.searchEmployee(motcle);
    }


    @GetMapping("/employee")
    public List<EmployeeResponse> getEmployeAndDepaetment(){
       return employeeService.getListEmployeeAndDepartment();
    }


}
