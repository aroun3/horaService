package com.visitor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.visitor.entities.ArrivalPunch;
import com.visitor.entities.RefreshArrival;

@Repository
public interface ArrivalPunchRepository extends JpaRepository<ArrivalPunch, Integer>{

	@Query(value = "select * from h_arrival_punch order by arrival_time asc limit 5", nativeQuery = true)
	List<ArrivalPunch> top5();
	
	@Query(value = "select * from h_arrival_punch order by arrival_time desc limit 5", nativeQuery = true)
	List<ArrivalPunch> last5();

	@Query(value = "select * from h_arrival_punch where arrival_state = '0' limit 10", nativeQuery = true)
	List<ArrivalPunch> findAbsent();
	
	List<ArrivalPunch> findByPunchStatus(String status);

	@Query("select count(ap.punchStatus) from ArrivalPunch ap where ap.punchStatus = :status")
	Integer count(@Param("status") String status);

	@Query("select distinct ap.area from ArrivalPunch ap")
	List<String> getArea();

	@Query("select count(ap.punchStatus) from ArrivalPunch ap where ap.punchStatus = :status and ap.area = :area")
	Integer countByArea(@Param("area") String area, @Param("status") String status);

	@Query(value = "select * from h_arrival_punch ap where ap.arrival_state <> '0' ORDER BY ap.arrival_time desc", nativeQuery = true)
	List<ArrivalPunch> arriveEnTempsReel();
	
	@Query(value = "select * from doarrivalrefresh() as status",nativeQuery = true)
	RefreshArrival refreshArrival();



	@Query(value = "select * from h_arrival_punch order by arrival_time asc ", nativeQuery = true)
	List<ArrivalPunch> getAllTop();

	@Query(value = "select * from h_arrival_punch order by arrival_time desc", nativeQuery = true)
	List<ArrivalPunch> getAllLast();

	@Query(value = "select * from h_arrival_punch where arrival_state = '0'", nativeQuery = true)
	List<ArrivalPunch> getAllAbsent();

}
