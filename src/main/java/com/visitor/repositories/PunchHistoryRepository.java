package com.visitor.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.visitor.entities.Area;
import com.visitor.entities.IArea;
import com.visitor.entities.PunchHistory;

public interface PunchHistoryRepository extends JpaRepository<PunchHistory, Integer>{

	@Query(value = "select count(ph.arrival_state) from punch_history ph where ph.arrival_state = :state and ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Integer countArrivalByState(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);
	
	@Query(value = "select count(ph.departure_state) from punch_history ph where ph.departure_state = :state and ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Integer countDepatureByState(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);

	@Query(value = "select count(ph.presence_state) from punch_history ph where ph.presence_state = :state and ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Integer countPresenceByState(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);
	
	@Query(value = "select count(ph.is_absent) from punch_history ph where ph.is_absent = :state and ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Integer countAbsent(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);
	
	
	@Query(value = "select count(ph.arrival_state) from punch_history ph where ph.arrival_state = :state and ph.arrival_terminal_id in :terminalId and ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Integer countArrivalByAreaAndState(@Param("terminalId") List<Integer> terminalId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);
	
	@Query(value = "select count(ph.departure_state) from punch_history ph where ph.departure_state = :state and ph.arrival_terminal_id in :terminalId and ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Integer countDepatureByAreaAndState(@Param("terminalId") List<Integer> terminalId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);

	@Query(value = "select count(ph.presence_state) from punch_history ph where ph.presence_state = :state and ph.arrival_terminal_id in :terminalId and ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Integer countPresenceByAreaAndState(@Param("terminalId") List<Integer> terminalId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);
	
	@Query(value = "select count(ph.is_absent) from punch_history ph where ph.is_absent = :state and ph.arrival_terminal_id in :terminalId and ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Integer countAbsentByArea(@Param("terminalId") List<Integer> terminalId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("state") String state);
	
	@Query(value = "select pa.id as id, pa.area_name as area from personnel_area pa", nativeQuery = true)
	List<IArea> findAreaList();

	@Query(value = "select pa.id from iclock_terminal pa where pa.area_id = :id", nativeQuery = true)
	List<Integer> findDepartmentByAreaId(@Param("id") Integer id);

	@Query(value = "select * from punch_history ph where ph.punch_day between :startDate and :endDate order by ph.arrival asc limit 1", nativeQuery = true)
	PunchHistory arrivalMin(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(value = "select * from punch_history ph where ph.punch_day between :startDate and :endDate order by ph.arrival desc limit 1", nativeQuery = true)
	PunchHistory arrivalMax(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "select avg(ph.arrival) from punch_history ph where ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Date arrivalMean(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "select * from punch_history ph where ph.punch_day between :startDate and :endDate order by ph.departure asc limit 1", nativeQuery = true)
	PunchHistory departureMin(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "select * from punch_history ph where ph.punch_day between :startDate and :endDate order by ph.departure desc limit 1", nativeQuery = true)
	PunchHistory departureMax(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "select avg(ph.departure) from punchhistory ph where ph.punch_day between :startDate and :endDate", nativeQuery = true)
	Date departureMean(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
