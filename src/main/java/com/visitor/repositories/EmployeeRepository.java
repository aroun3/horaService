package com.visitor.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.visitor.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository Employee
 */
@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    
    @Query(value="SELECT * FROM h_personnel_employee_view LIMIT 1",nativeQuery = true)
    public List<Employee> finnAll();
}
