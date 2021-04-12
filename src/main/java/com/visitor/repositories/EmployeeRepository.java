package com.visitor.repositories;

import java.util.Date;
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


    Optional<Employee> findByEmpCode(String  codeEmploye);

    @Query(value = "SELECT pe.emp_code, pe.first_name, pe.last_name, pd.dept_name, pp.position_name, pe.gender, pe.mobile, STRING_AGG(pa.area_name,',') FROM personnel_employee pe \n" +
            " LEFT JOIN personnel_department pd ON  pd.id = pe.department_id " +
            " LEFT JOIN personnel_position pp ON pp.id = pe.position_id" +
            " LEFT JOIN personnel_employee_area pea ON pea.employee_id = pe.id\n" +
            " LEFT JOIN personnel_area pa ON pa.id = pea.area_id " +
            " GROUP BY pe.emp_code, pe.first_name, pe.last_name,pd.dept_name, pp.position_name,pe.gender, pe.mobile", nativeQuery = true)
    List<Object[]> getAllEmployee();

    @Query(value ="SELECT pe.emp_code, pe.first_name, pe.last_name, pd.dept_name, pp.position_name, pe.gender, pe.mobile, STRING_AGG(pa.area_name,',') FROM personnel_employee pe \n" +
            " INNER JOIN personnel_department pd ON  pd.id = pe.department_id " +
            " INNER JOIN personnel_position pp ON pp.id = pe.position_id" +
            " INNER JOIN personnel_employee_area pea ON pea.employee_id = pe.id\n" +
            " INNER JOIN personnel_area pa ON pa.id = pea.area_id " +
            " WHERE pe.emp_code= :empCode "+
            " GROUP BY pe.emp_code, pe.first_name, pe.last_name,pd.dept_name, pp.position_name,pe.gender, pe.mobile", nativeQuery = true)
    List<Object[]> getEmployeeByCode(@Param("empCode") String empCode);

    @Query(value ="SELECT pe.emp_code, pe.first_name, pe.last_name, pe.gender, pe.mobile FROM personnel_employee pe \n" +
            " WHERE LOWER(pe.first_name) like lower(concat( :motcle,'%')) OR LOWER(pe.last_name) like lower(concat( :motcle,'%')) ", nativeQuery = true)
    List<Object[]> searchEmployee(@Param("motcle") String motcle);




    /*LISTE DES COMPTES ARRIVE,PRESENT, ABSENT POUR UN EMPLOYEE*/

    @Query(value = "select count(ph.arrival_state) "
            + " from h_log_transaction ph where ph.arrival_state = :state and ph.log_date between :startDate and :endDate AND ph.emp_code = :empCode", nativeQuery = true)
    Integer countArrivalByState(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state, @Param("empCode") String empCode);

    @Query(value = "select count(ph.departure_state) "
            + " from h_log_transaction ph "
            + " where ph.departure_state = :state and ph.log_date between :startDate and :endDate AND ph.emp_code = :empCode", nativeQuery = true)
    Integer countDepatureByState(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state, @Param("empCode") String empCode);

    @Query(value = "select count(ph.presence_state) "
            + "from h_log_transaction ph "
            + "where ph.presence_state = :state and ph.log_date between :startDate and :endDate AND ph.emp_code = :empCode", nativeQuery = true)
    Integer countPresenceByState(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state, @Param("empCode") String empCode);

    @Query(value = "select count(ph.is_absent) from h_log_transaction ph where ph.is_absent = :state and ph.log_date between :startDate and :endDate AND ph.emp_code = :empCode", nativeQuery = true)
    Integer countAbsent(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") Boolean state, @Param("empCode") String empCode);


}
