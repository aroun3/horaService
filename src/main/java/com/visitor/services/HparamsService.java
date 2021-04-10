package com.visitor.services;

import com.visitor.entities.Hparams;
import com.visitor.entities.User;
import com.visitor.repositories.HparamsRepository;
import com.visitor.repositories.UserRepository;
import com.visitor.service_interfaces.HparamsInterface;
import com.visitor.service_interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("hparamsInterface")
public class HparamsService implements HparamsInterface {

    @Autowired
    private HparamsRepository hparamsRepository;

    @Override
    public List<Hparams> getAll() {
        return hparamsRepository.findAll();
    }

    @Override
    public Hparams add(Hparams hparams) {
        return hparamsRepository.save(hparams);
    }

    @Override
    public Hparams update(Hparams hparams) {
        if(hparams.getId() ==0){
           return hparamsRepository.saveAndFlush(hparams);
        }
        return hparamsRepository.save(hparams);
    }

    @Override
    public Hparams getOneById(Integer id) {
        Optional<Hparams> hparams= hparamsRepository.findById(id);
        return  hparams.get();
    }

    @Override
    public void delete(Integer id) {
            hparamsRepository.deleteById(id);
    }
}


