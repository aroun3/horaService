package com.visitor.repositories;

import java.util.Date;
import java.util.List;

import com.visitor.entities.HistoryPointage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.visitor.entities.IArea;
import com.visitor.entities.IPunchHistory;
import com.visitor.entities.PunchHistory;

public interface PunchHistoryRepository extends JpaRepository<PunchHistory, Integer>{

	@Query(value = "select count(ph.arrival_state) "
			+ " from h_log_transaction ph where ph.arrival_state = :state and ph.log_date between :startDate and :endDate", nativeQuery = true)
	Integer countArrivalByState(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);
	
	@Query(value = "select count(ph.departure_state) "
			+ " from h_log_transaction ph "
			+ " where ph.departure_state = :state and ph.log_date between :startDate and :endDate", nativeQuery = true)
	Integer countDepatureByState(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);

	@Query(value = "select count(ph.presence_state) "
			+ "from h_log_transaction ph "
			+ "where ph.presence_state = :state and ph.log_date between :startDate and :endDate", nativeQuery = true)
	Integer countPresenceByState(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);
	
	@Query(value = "select count(ph.is_absent) from h_log_transaction ph where ph.is_absent = :state and ph.log_date between :startDate and :endDate", nativeQuery = true)
	Integer countAbsent(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") Boolean state);
	
	
	
	
	
	
	@Query(value = "select count(ph.arrival_state) from "
			+ " h_log_transaction ph, personnel_employee pe, personnel_employee_area pae "
			+ " where pae.employee_id = pe.id and ph.emp_code = pe.emp_code "
			+ " and ph.arrival_state = :state and pae.area_id = :areAId and ph.log_date between :startDate and :endDate", nativeQuery = true)
	Integer countArrivalByAreaAndState(@Param("areAId") Integer areAId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);
	
	@Query(value = "select count(ph.departure_state) "
			+ " from h_log_transaction ph, personnel_employee pe, personnel_employee_area pae "
			+ " where pae.employee_id = pe.id and ph.emp_code = pe.emp_code "
			+ " and ph.departure_state = :state and pae.area_id = :areAId and ph.log_date between :startDate and :endDate", nativeQuery = true)
	Integer countDepatureByAreaAndState(@Param("areAId") Integer areAId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);

	@Query(value = "select count(ph.presence_state) "
			+ " from h_log_transaction ph, personnel_employee pe, personnel_employee_area pae "
			+ " where pae.employee_id = pe.id and ph.emp_code = pe.emp_code "
			+ " and ph.presence_state = :state and pae.area_id = :areAId and ph.log_date between :startDate and :endDate", nativeQuery = true)
	Integer countPresenceByAreaAndState(@Param("areAId") Integer areAId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);
	
	@Query(value = "select count(ph.is_absent) "
			+ " from h_log_transaction ph, personnel_employee pe, personnel_employee_area pae "
			+ " where pae.employee_id = pe.id and ph.emp_code = pe.emp_code "
			+ " and ph.is_absent = :state and pae.area_id = :areAId and ph.log_date between :startDate and :endDate", nativeQuery = true)
	Integer countAbsentByArea(@Param("areAId") Integer areAId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") Boolean state);
	
	
	
	
	
	@Query(value = "select pe.emp_code as empCode, ph.arrival_time as arrivalTime, ph.arrival_id as arrivalId, ph.arrival_terminal_id as arrivalTerminalId,"
			+ " ph.departure_time as departureTime, ph.departure_id as departureId, ph.departure_terminal_id as departureTerminalId,"
			+ " ph.presence_periode as presencePeriode, ph.arrival_state as arrivalState, ph.departure_state as departureState, ph.presence_state as presenceState,"
			+ " ph.is_absent as isAbsent, ph.log_date as logDate, pe.first_name as firstName, pe.last_name as lastName, "
			+ " pp.position_name as position, pd.dept_name as departement "
			+ " from h_log_transaction ph, personnel_employee pe, personnel_employee_area pae, personnel_department pd, personnel_position pp"
			+ " where pp.id = pe.position_id and pd.id = pe.department_id and pae.employee_id = pe.id and ph.emp_code = pe.emp_code "
			+ " and ph.arrival_state = '1' and ph.log_date between :startDate and :endDate order by ph.arrival_time asc limit 5", nativeQuery = true)
	List<IPunchHistory> arrivalMin5(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(value = "select pe.emp_code as empCode, ph.arrival_time as arrivalTime, ph.arrival_id as arrivalId, ph.arrival_terminal_id as arrivalTerminalId,"
			+ " ph.departure_time as departureTime, ph.departure_id as departureId, ph.departure_terminal_id as departureTerminalId,"
			+ " ph.presence_periode as presencePeriode, ph.arrival_state as arrivalState, ph.departure_state as departureState, ph.presence_state as presenceState,"
			+ " ph.is_absent as isAbsent, ph.log_date as logDate, pe.first_name as firstName, pe.last_name as lastName, "
			+ " pp.position_name as position, pd.dept_name as departement "
			+ " from h_log_transaction ph, personnel_employee pe, personnel_employee_area pae, personnel_department pd, personnel_position pp"
			+ " where pp.id = pe.position_id and pd.id = pe.department_id and pae.employee_id = pe.id and ph.emp_code = pe.emp_code "
			+ " and ph.arrival_state = '3' and ph.log_date between :startDate and :endDate order by ph.arrival_time desc limit 5", nativeQuery = true)
	List<IPunchHistory> arrivalMax5(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "select pe.emp_code as empCode, ph.arrival_time as arrivalTime, ph.arrival_id as arrivalId, ph.arrival_terminal_id as arrivalTerminalId,"
			+ " ph.departure_time as departureTime, ph.departure_id as departureId, ph.departure_terminal_id as departureTerminalId,"
			+ " ph.presence_periode as presencePeriode, ph.arrival_state as arrivalState, ph.departure_state as departureState, ph.presence_state as presenceState,"
			+ " ph.is_absent as isAbsent, ph.log_date as logDate, pe.first_name as firstName, pe.last_name as lastName, "
			+ " pp.position_name as position, pd.dept_name as departement "
			+ " from h_log_transaction ph, personnel_employee pe, personnel_employee_area pae, personnel_department pd, personnel_position pp"
			+ " where pp.id = pe.position_id and pd.id = pe.department_id and pae.employee_id = pe.id and ph.emp_code = pe.emp_code "
			+ " and ph.departure_state = '1' and ph.log_date between between :startDate and :endDate order by ph.departure_time asc limit 5", nativeQuery = true)
	List<IPunchHistory> departureMin5(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "select pe.emp_code as empCode, ph.arrival_time as arrivalTime, ph.arrival_id as arrivalId, ph.arrival_terminal_id as arrivalTerminalId,"
			+ " ph.departure_time as departureTime, ph.departure_id as departureId, ph.departure_terminal_id as departureTerminalId,"
			+ " ph.presence_periode as presencePeriode, ph.arrival_state as arrivalState, ph.departure_state as departureState, ph.presence_state as presenceState,"
			+ " ph.is_absent as isAbsent, ph.log_date as logDate, pe.first_name as firstName, pe.last_name as lastName, "
			+ " pp.position_name as position, pd.dept_name as departement "
			+ " from h_log_transaction ph, personnel_employee pe, personnel_employee_area pea, personnel_department pd, personnel_position pp"
			+ " where pp.id = pe.position_id and pd.id = pe.department_id and pae.employee_id = pe.id and ph.emp_code = pe.emp_code "
			+ " and ph.departure_state = '3' and ph.log_date between between :startDate and :endDate order by ph.departure_time desc limit 5", nativeQuery = true)
	List<IPunchHistory> departureMax5(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	
	
	
	
	
	
	
	
	@Query(value = "select avg(ph.departure) from punchhistory ph where ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Date departureMean(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(value = "select pa.id as id, pa.area_name as area, ag.longitude as longitude, ag.latitude as latitude "
			+ " from personnel_area pa, h_gps_area ag where pa.id = ag.area_id", nativeQuery = true)
	List<IArea> findAreaList();

	@Query(value = "select pa.id from iclock_terminal pa where pa.area_id = :id", nativeQuery = true)
	List<Integer> findDepartmentByAreaId(@Param("id") Integer id);

	@Query(value = "select avg(ph.arrival) from punch_history ph where ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Date arrivalMean(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "select pe.emp_code as empCode, ph.arrival_time as arrivalTime, ph.arrival_id as arrivalId, ph.arrival_terminal_id as arrivalTerminalId(),"
			+ " ph.departure_time as departureTime, ph.departure_id as departureId, ph.departure_terminal_id as departureTerminalId,"
			+ " ph.presence_periode as presencePeriode, ph.arrival_state as arrivalState, ph.departure_state as departureState, ph.presence_state as presenceState,"
			+ " ph.is_absent as isAbsent, ph.log_date as logDate, pe.first_name as firstName, pe.last_name as lastName, "
			+ " pp.position_name as position, pd.dept_name as departement "
			+ " from h_log_transaction ph, personnel_employee pe, personnel_employee_area pea, personnel_department pd, personnel_position pp"
			+ " where pp.id = pe.position_id and pd.id = pe.department_id and pae.employee_id = pe.id and ph.emp_code = pe.emp_code "
			+ " and ph.is_absent = false and ph.log_date between between :startDate and :endDate order by ph.presence_periode desc limit 5", nativeQuery = true)
	List<IPunchHistory> present5(Date startDate, Date endDate);

	@Query(value = "select pe.emp_code as empCode, ph.arrival_time as arrivalTime, ph.arrival_id as arrivalId, ph.arrival_terminal_id as arrivalTerminalId(),"
			+ " ph.departure_time as departureTime, ph.departure_id as departureId, ph.departure_terminal_id as departureTerminalId,"
			+ " ph.presence_periode as presencePeriode, ph.arrival_state as arrivalState, ph.departure_state as departureState, ph.presence_state as presenceState,"
			+ " ph.is_absent as isAbsent, ph.log_date as logDate, pe.first_name as firstName, pe.last_name as lastName, "
			+ " pp.position_name as position, pd.dept_name as departement "
			+ " from h_log_transaction ph, personnel_employee pe, personnel_employee_area pea, personnel_department pd, personnel_position pp"
			+ " where pp.id = pe.position_id and pd.id = pe.department_id and pae.employee_id = pe.id and ph.emp_code = pe.emp_code "
			+ " and ph.is_absent = true and ph.log_date between between :startDate and :endDate", nativeQuery = true)
	List<IPunchHistory> absent(Date startDate, Date endDate);


	/*===============================================
	DETAIL PAR EMPLOYEE(EmpCode)
	===============================================*/

	@Query(value = "select count(ph.arrival_state) "
			+ " from h_log_transaction ph where ph.arrival_state = :state and ph.log_date between :startDate and :endDate AND ph.emp_code= :empCode", nativeQuery = true)
	Integer countArrivalByStateAndEmpCode(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state,String empCode);

	@Query(value = "select count(ph.departure_state) "
			+ " from h_log_transaction ph "
			+ " where ph.departure_state = :state and ph.log_date between :startDate and :endDate AND ph.emp_code= :empCode", nativeQuery = true)
	Integer countDepatureByStateAndEmpCode(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state,String empCode);

	@Query(value = "select count(ph.presence_state) "
			+ " from h_log_transaction ph "
			+ " where ph.presence_state = :state and ph.log_date between :startDate and :endDate AND ph.emp_code= :empCode", nativeQuery = true)
	Integer countPresenceByStateAndEmpCode(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state,String empCode);

	@Query(value = "select count(ph.is_absent) from h_log_transaction ph where ph.is_absent = :state and ph.log_date between :startDate and :endDate AND ph.emp_code= :empCode", nativeQuery = true)
	Integer countAbsentAndEmpCode(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") Boolean state,String empCode);

	/*@Query(value = "SELECT hlt.id as id , pe.emp_code as empCode ,pe.first_name, pe.gender, pe.email, pe.mobile, pe.last_name, STRING_AGG(pa.area_name, ','), hlt.arrival_time, hlt.arrival_state, hlt.departure_time, hlt.departure_state, hlt.presence_periode FROM h_log_transaction hlt\n" +
			"INNER JOIN personnel_employee pe ON hlt.emp_code = pe.emp_code " +
			"INNER JOIN personnel_employee_area pea ON pea.employee_id = pe.id " +
			"INNER JOIN personnel_area pa ON pa.id = pea.area_id " +
			"WHERE pe.emp_code = :empCode " +
			"GROUP BY hlt.id, pe.emp_code,pe.first_name, pe.gender, pe.email, pe.mobile, pe.last_name",nativeQuery = true)
	List<HistoryPointage>historyPointageByEmpCode(@Param("empCode") String empCode);*/

}
