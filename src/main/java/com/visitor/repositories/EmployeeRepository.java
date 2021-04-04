package com.visitor.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.visitor.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository Employee
 */
@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    
    @Query(value="SELECT * FROM h_personnel_employee_view LIMIT 1",nativeQuery = true)
    public List<Employee> finnAll();

    @Query(value = "select pe.emp_code as emp_code, pe.first_name as first_name, last_name as last_name, pe.photo as photo, pe.gender as gender, "
    		+ "pe.contact_tel as contact_tel, pe.mobile as mobile, pe.city as city, pe.status as status, pe.address as address, pe.department_id as dep_id "
    		+ "from personnel_employee pe where pe.emp_code = :codeEmp",nativeQuery = true)
	Optional<Employee> findByEmpCode(@Param("codeEmp") String codeEmploye);
}
