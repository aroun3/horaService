package com.visitor.repositories;

import com.visitor.entities.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteRepository extends JpaRepository<Carte, Integer> {
    public Optional<Carte> findById(Integer id);
}
