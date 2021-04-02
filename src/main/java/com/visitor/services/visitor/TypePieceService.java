package com.visitor.services.visitor;

import com.visitor.entities.visitor.TypePiece;
import com.visitor.repositories.TypePieceRepository;
import com.visitor.service_interfaces.TypePieceServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("typePieceService")
public class TypePieceService implements TypePieceServiceInterface {
    @Autowired
    private TypePieceRepository typePieceRepository;

    @Override
    public List<TypePiece> getAll() {
        return typePieceRepository.findAll();
    }

    @Override
    public TypePiece add(TypePiece cypePiece) {
        return typePieceRepository.save(cypePiece);
    }

    @Override
    public TypePiece update(TypePiece cypePiece) {
        if(cypePiece.getId() ==0) {
            return typePieceRepository.save(cypePiece);
        }
        return  typePieceRepository.saveAndFlush(cypePiece);
    }

    @Override
    public TypePiece getOneById(Integer id) {
        return typePieceRepository.getOne(id);
    }

    @Override
    public void delete(Integer id) {
        typePieceRepository.deleteById(id);
    }
}
