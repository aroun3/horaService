package com.visitor.serviceImpl;

import com.visitor.entities.Carte;
import com.visitor.repositories.CarteRepository;
import com.visitor.service.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carteService")
public class CarteServiceImpl implements CarteService {
    @Autowired
    private CarteRepository carteRepository;

    @Override
    public List<Carte> getAll() {
        return carteRepository.findAll();
    }

    @Override
    public Carte add(Carte carte) {
        return carteRepository.save(carte);
    }

    @Override
    public Carte update(Carte carte) {
        if(carte.getId() ==0) {
            return carteRepository.save(carte);
        }
        return  carteRepository.saveAndFlush(carte);
    }

    @Override
    public Carte getOneById(Integer id) {
        return carteRepository.getOne(id);
    }

    @Override
    public void delete(Integer id) {
        carteRepository.deleteById(id);
    }
}
