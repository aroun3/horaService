package com.visitor.service_interfaces;

import com.visitor.entities.Employee;
import com.visitor.payload.response.EmployeeResponse;

import java.util.List;

public interface EmployeeServiceInterface {
    public List<Employee> findAll();
    public List<EmployeeResponse> getListEmployeeAndDepartment();
}
