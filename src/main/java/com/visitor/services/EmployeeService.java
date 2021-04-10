package com.visitor.services;

import com.visitor.entities.Employee;
import com.visitor.payload.response.EmployeeResponse;
import com.visitor.repositories.EmployeeRepository;
import com.visitor.service_interfaces.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

   /* @Override
    public List<EmployeeResponse> findByFirstNameOrLastName(String firstName, String lastName) {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Object[]> employeeList =  employeeRepository.findByFirstNameOrLastName(firstName, lastName);
        System.out.println("emp list "+employeeList);
        for(Object[] rs : employeeList){
            EmployeeResponse emp  = new EmployeeResponse();
            emp.setFirstName(rs[0]+"");
            emp.setLastName(rs[1]+"");
            emp.setDepartment(rs[2]+"");
            employeeResponseList.add(emp);
        }
        return employeeResponseList;
    }*/

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Object[]> employeeList =  employeeRepository.getAllEmployee();
        for(Object[] rs : employeeList){
            EmployeeResponse emp  = new EmployeeResponse();
            emp.setEmpCode(rs[0]+"");
            emp.setFirstName(rs[1]+"");
            emp.setLastName(rs[2]+"");
            emp.setDepartment(rs[3]+"");
            emp.setFonction(rs[4]+"");
            emp.setGender(rs[5]+"");
            emp.setMobile(rs[6]+"");
            emp.setCity(rs[7]+"");
            employeeResponseList.add(emp);
        }
        return employeeResponseList;
    }

    @Override
    public List<EmployeeResponse> getEmployeeByCode(String empCode) {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Object[]> employeeList =  employeeRepository.getEmployeeByCode(empCode);
        for(Object[] rs : employeeList){
            EmployeeResponse emp  = new EmployeeResponse();
            emp.setEmpCode(rs[0]+"");
            emp.setFirstName(rs[1]+"");
            emp.setLastName(rs[2]+"");
            emp.setDepartment(rs[3]+"");
            emp.setFonction(rs[4]+"");
            emp.setGender(rs[5]+"");
            emp.setMobile(rs[6]+"");
            emp.setCity(rs[7]+"");
            employeeResponseList.add(emp);
        }
        return employeeResponseList;
    }

    @Override
    public List<EmployeeResponse> searchEmployee(String firstName) {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Object[]> employeeList =  employeeRepository.searchEmployee(firstName);
        for(Object[] rs : employeeList){
            EmployeeResponse emp  = new EmployeeResponse();
            emp.setEmpCode(rs[0]+"");
            emp.setFirstName(rs[1]+"");
            emp.setLastName(rs[2]+"");
            emp.setGender(rs[3]+"");
            emp.setMobile(rs[4]+"");
            employeeResponseList.add(emp);
        }
        return employeeResponseList;
    }
}
