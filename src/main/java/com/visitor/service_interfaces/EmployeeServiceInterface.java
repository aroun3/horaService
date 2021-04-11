package com.visitor.service_interfaces;

import com.visitor.entities.Employee;
import com.visitor.payload.response.EmployeeResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeServiceInterface {
    public List<Employee> findAll();
    public List<EmployeeResponse> getListEmployeeAndDepartment();
    List<EmployeeResponse> getAllEmployee();
    List<EmployeeResponse> getEmployeeByCode(String empCode);
    List<EmployeeResponse> searchEmployee(String motcle);
}
