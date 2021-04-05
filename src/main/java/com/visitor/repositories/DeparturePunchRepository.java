package com.visitor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visitor.entities.DeparturePunch;

@Repository
public interface DeparturePunchRepository extends JpaRepository<DeparturePunch, Integer>{

}
