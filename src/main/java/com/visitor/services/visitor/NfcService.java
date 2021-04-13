package com.visitor.services.visitor;

import com.visitor.entities.visitor.Nfc;
import com.visitor.repositories.visitor.NfcRepository;
import com.visitor.service_interfaces.NfcInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service("nfcInterface")
public class NfcService implements NfcInterface {
    @Autowired
    private NfcRepository nfcRepository;

    @Override
    public List<Nfc> getAll() {
        return nfcRepository.findAll();
    }

    @Override
    @Transactional
    public Nfc add(Nfc nfc) {
        Random random = new Random();
        Integer val = random.nextInt();
        Integer index = nfcRepository.lastIndexNfc();
        String rand =(val+index)+"";
        nfc.setNfcRef(rand.replace("-",""));
        nfc.setStatus(false);
        return nfcRepository.save(nfc);
    }

    @Override
    public Nfc update(Nfc nfc) {
        if(nfc.getId()==0){
          return nfcRepository.save(nfc);
        }
        return nfcRepository.saveAndFlush(nfc);
    }

    @Override
    public Nfc getOneById(Integer id) {
        Optional<Nfc> nfc = nfcRepository.findById(id);
        return  nfc.get();
    }

    @Override
    public void delete(Integer id) {
        nfcRepository.deleteById(id);
    }

    @Override
    public Nfc findByNfcId(String nfcRef) {
        return nfcRepository.findByNfcId(nfcRef);
    }

    @Override
    public Nfc findByNfcRef(String nfcRef) {
        return nfcRepository.findByNfcRef(nfcRef);
    }
}
