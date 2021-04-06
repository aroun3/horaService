package com.visitor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visitor.entities.AreaGps;

@Repository
public interface AreaGpsRepository extends JpaRepository<AreaGps, Integer>{

	List<AreaGps>findByArea(String area);
}
