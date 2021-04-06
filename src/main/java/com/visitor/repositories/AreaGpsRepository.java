package com.visitor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.visitor.entities.AreaGps;
import com.visitor.entities.IArea;

@Repository
public interface AreaGpsRepository extends JpaRepository<AreaGps, Integer>{

	@Query(value = "select pa.id as id, pa.area_name as area, ag.longitude as longitude, ag.latitude as latitude "
			+ "from personnel_area pa, h_area_gps ag "
			+ "where pa.id = ag.area_id pa.area_name = :area", nativeQuery = true)
	List<IArea> findByArea(@Param("area") String area);
}
