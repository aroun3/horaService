package com.visitor.repositories;

import java.util.List;

import com.visitor.payload.response.PersonnelArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.visitor.entities.AreaGps;
import com.visitor.entities.IArea;

@Repository
public interface AreaGpsRepository extends JpaRepository<AreaGps, Integer>{

	@Query(value = "select pa.id as id, pa.area_name as area, ag.longitude as longitude, ag.latitude as latitude "
			+ "from personnel_area pa, h_gps_area ag "
			+ "where pa.id = ag.area_id and pa.area_name = :area", nativeQuery = true)
	List<IArea> findByArea(@Param("area") String area);

	@Query(value = "SELECT pa.id, pa.area_code, pa.area_name, pa.parent_area_id FROM personnel_area pa ", nativeQuery = true)
	public List<Object[]> getAllEmployeArea();
}
