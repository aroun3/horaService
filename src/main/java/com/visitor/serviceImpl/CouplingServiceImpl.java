package com.visitor.serviceImpl;

import com.visitor.entities.Coupling;
import com.visitor.repositories.CouplingRepository;
import com.visitor.service.CouplingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("couplingService")
public class CouplingServiceImpl implements CouplingService {
    @Autowired
    private CouplingRepository couplingRepository;

    @Override
    public List<Coupling> getAll() {
        return couplingRepository.findAll();
    }

    @Override
    public Coupling add(Coupling coupling) {
        return couplingRepository.save(coupling);
    }

    @Override
    public Coupling update(Coupling coupling) {
        if(coupling.getId() ==0) {
            return couplingRepository.save(coupling);
        }
        return  couplingRepository.saveAndFlush(coupling);
    }

    @Override
    public Coupling getOneById(Integer id) {
        return couplingRepository.getOne(id);
    }

    @Override
    public void delete(Integer id) {
        couplingRepository.deleteById(id);
    }
}
