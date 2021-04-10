package com.visitor.repositories.visitor;


import com.visitor.entities.visitor.Nfc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NfcRepository extends JpaRepository<Nfc, Integer> {
    public Optional<Nfc> findById(Integer id);
    public Nfc findByNfcId(String nfcId);
    public Nfc findByNfcRef(String nfcRef);

}
