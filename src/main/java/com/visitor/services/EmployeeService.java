package com.visitor.services;

import com.visitor.entities.Employee;
import com.visitor.payload.response.EmployeeResponse;
import com.visitor.repositories.EmployeeRepository;
import com.visitor.service_interfaces.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("employeeServiceInterface")
public class EmployeeService implements EmployeeServiceInterface {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<EmployeeResponse> getListEmployeeAndDepartment() {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Object[]> employeeList =  employeeRepository.getListEmployeeAndDepartment();
        for(Object[] rs : employeeList){
            EmployeeResponse emp  = new EmployeeResponse();
            emp.setFirstName(rs[0]+"");
            emp.setLastName(rs[1]+"");
            emp.setDepartment(rs[2]+"");
            employeeResponseList.add(emp);
        }
        return employeeResponseList;
    }

    @Override
    public List<EmployeeResponse> findByFirstNameOrLastName(String firstName, String lastName) {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Object[]> employeeList =  employeeRepository.findByFirstNameOrLastName(firstName, lastName);
        for(Object[] rs : employeeList){
            EmployeeResponse emp  = new EmployeeResponse();
            emp.setFirstName(rs[0]+"");
            emp.setLastName(rs[1]+"");
            emp.setDepartment(rs[2]+"");
            employeeResponseList.add(emp);
        }
        return employeeResponseList;
    }
}
