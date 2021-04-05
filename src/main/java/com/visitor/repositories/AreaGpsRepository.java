package com.visitor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.visitor.entities.AreaGps;

public interface AreaGpsRepository extends JpaRepository<AreaGps, Integer>{

	List<AreaGps> FindByArea(String area);
}
