package com.visitor.repositories;

import com.visitor.entities.Carte;
import com.visitor.entities.TypePiece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypePieceRepository extends JpaRepository<TypePiece, Integer> {
    public Optional<TypePiece> findById(Integer id);
}
