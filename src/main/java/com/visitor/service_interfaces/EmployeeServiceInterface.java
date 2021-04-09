package com.visitor.service_interfaces;

import com.visitor.entities.Employee;
import com.visitor.payload.response.EmployeeResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeServiceInterface {
    public List<Employee> findAll();
    public List<EmployeeResponse> getListEmployeeAndDepartment();
    public List<EmployeeResponse> findByFirstNameOrLastName(String firstName, String lastName);
    List<EmployeeResponse> getAllEmployee();
    List<EmployeeResponse> getEmployeeByCode(@Param("empCode") String empCode);
}
