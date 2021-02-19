package com.visitor.repositories;

import com.visitor.entities.Personnal;
import com.visitor.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonnalRepository extends JpaRepository<Personnal, Integer> {
    public Optional<Personnal> findById(Integer id);
}
