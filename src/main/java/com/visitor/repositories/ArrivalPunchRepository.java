package com.visitor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.visitor.entities.ArrivalPunch;

@Repository
public interface ArrivalPunchRepository extends JpaRepository<ArrivalPunch, Integer>{

	@Query("select ap from ArrivalPunch ap where ap.punchStatus = 'EARLY' order by ap.arrivalTime asc limit 5")
	List<ArrivalPunch> top5();
	
	@Query("select ap from ArrivalPunch ap where ap.punchStatus = 'LATE' order by ap.arrivalTime desc limit 5")
	List<ArrivalPunch> last5();

	List<ArrivalPunch> findByPunchStatus(String status);

	@Query("select count(ap.punchStatus) from ArrivalPunch ap where ap.punchStatus = :status")
	Integer count(@Param("status") String status);

	@Query("select distinct ap.area from ArrivalPunch ap")
	List<String> getArea();

	@Query("select count(ap.punchStatus) from ArrivalPunch ap where ap.punchStatus = :status and ap.area = :area")
	Integer countByArea(@Param("area") String area, @Param("status") String status);

}
