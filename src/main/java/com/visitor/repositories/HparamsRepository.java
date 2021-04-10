package com.visitor.repositories;

import com.visitor.entities.Hparams;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by olivier on 16/09/2020.
 */
@Repository
public interface HparamsRepository extends JpaRepository<Hparams, Integer> {
    Optional<Hparams> findById(Integer id);


}
