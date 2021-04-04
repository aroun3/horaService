package com.visitor.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.visitor.entities.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, String>{
	
	@Query(value = "select dp.dept_code as dept_code, dp.dept_name as dept_name from personnel_department dp where dp.id = :idDep",nativeQuery = true)
	Optional<Departement> findById(@Param("idDep") Integer idDep);

}
