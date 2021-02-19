package com.visitor.serviceImpl;

import com.visitor.entities.Personnal;
import com.visitor.repositories.PersonnalRepository;
import com.visitor.service.PersonnalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personnalService")
public class PersonnalServiceImpl implements PersonnalService {
    @Autowired
    private PersonnalRepository personnalRepository;

    @Override
    public List<Personnal> getAll() {
        return personnalRepository.findAll();
    }

    @Override
    public Personnal add(Personnal personnal) {
        return personnalRepository.save(personnal);
    }

    @Override
    public Personnal update(Personnal personnal) {
        if(personnal.getId() ==0) {
            return personnalRepository.save(personnal);
        }
        return  personnalRepository.saveAndFlush(personnal);
    }

    @Override
    public Personnal getOneById(Integer id) {
        return personnalRepository.getOne(id);
    }

    @Override
    public void delete(Integer id) {
        personnalRepository.deleteById(id);
    }
}
