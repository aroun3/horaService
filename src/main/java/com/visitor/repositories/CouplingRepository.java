package com.visitor.repositories;

import com.visitor.entities.Coupling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouplingRepository extends JpaRepository<Coupling, Integer> {
    public Optional<Coupling> findById(Integer id);

}
