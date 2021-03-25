package com.visitor.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.visitor.entities.RealTimeTransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RealTimeTransactionRepository extends JpaRepository<RealTimeTransaction,Long>{
    
    public List<RealTimeTransaction> findByPunchDate(Date date);
}