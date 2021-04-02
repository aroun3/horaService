package com.visitor.repositories.visitor;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.visitor.entities.visitor.Coupling;

@Repository
public interface CouplingRepository extends JpaRepository<Coupling, Integer> {
    public Optional<Coupling> findById(Integer id);

}
