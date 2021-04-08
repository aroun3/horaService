package com.visitor.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.visitor.entities.Employee;

import com.visitor.payload.response.EmployeeResponse;
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
    public List<Employee> findAll();

    @Query(value ="SELECT pe.first_name, pe.last_name, pd.dept_name FROM personnel_employee pe\n" +
            "INNER JOIN personnel_department pd ON  pd.id = pe.department_id", nativeQuery = true)
    public List<Object[]> getListEmployeeAndDepartment();

    @Query(value ="SELECT pe.first_name, pe.last_name, pd.dept_name FROM personnel_employee pe\n" +
            "INNER JOIN personnel_department pd ON  pd.id = pe.department_id \n" +
            "WHERE pe.first_name = :firstName OR pe.last_name = :lastName", nativeQuery = true)
    List<Object[]> findByFirstNameOrLastName(@Param("firstName")String firstName, @Param("lastName")String lastName);

    Optional<Employee> findByEmpCode(String  codeEmploye);

    @Query(value = "SELECT pe.emp_code, pe.first_name, pe.last_name, pd.dept_name, pp.position_name, pe.gender, pe.mobile, STRING_AGG(pa.area_name,',') FROM personnel_employee pe \n" +
            " INNER JOIN personnel_department pd ON  pd.id = pe.department_id " +
            " INNER JOIN personnel_position pp ON pp.id = pe.position_id" +
            " INNER JOIN personnel_employee_area pea ON pea.employee_id = pe.id\n" +
            " INNER JOIN personnel_area pa ON pa.id = pea.area_id " +
            " GROUP BY pe.emp_code, pe.first_name, pe.last_name,pd.dept_name, pp.position_name,pe.gender, pe.mobile", nativeQuery = true)
    List<Object[]> getAllEmployee();

    @Query(value = "SELECT pe.emp_code, pe.first_name, pe.last_name, pd.dept_name, pp.position_name, pe.gender, pe.mobile, STRING_AGG(pa.area_name,',') FROM personnel_employee pe \n" +
            " INNER JOIN personnel_department pd ON  pd.id = pe.department_id " +
            " INNER JOIN personnel_position pp ON pp.id = pe.position_id" +
            " INNER JOIN personnel_employee_area pea ON pea.employee_id = pe.id\n" +
            " INNER JOIN personnel_area pa ON pa.id = pea.area_id " +
            " WHERE pe.emp_code= :empCode "+
            " GROUP BY pe.emp_code, pe.first_name, pe.last_name,pd.dept_name, pp.position_name,pe.gender, pe.mobile", nativeQuery = true)
    List<Object[]> getEmployeeByCode(@Param("empCode") String empCode);



}
