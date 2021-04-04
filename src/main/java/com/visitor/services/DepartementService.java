package com.visitor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.entities.Departement;
import com.visitor.repositories.DepartementRepository;

@Service
public class DepartementService {

	@Autowired
	private DepartementRepository departementRepository;
	
	public Departement findById(Integer idDep) {
		Optional<Departement> deOptional = departementRepository.findById(idDep);
		return deOptional.isPresent()? deOptional.get() : null;
	}

}
