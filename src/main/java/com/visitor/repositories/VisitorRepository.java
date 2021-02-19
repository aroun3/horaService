package com.visitor.repositories;

import com.visitor.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    public Optional<Visitor> findById(Integer id);
    @Query(value = "SELECT COUNT(*) FROM article",nativeQuery = true)
    public Integer countVisitor();
}
