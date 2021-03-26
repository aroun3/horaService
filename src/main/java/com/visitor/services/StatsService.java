package com.visitor.services;

import java.util.Date;
import java.util.List;

import com.visitor.entities.RealTimeTransaction;
import com.visitor.repositories.RealTimeTransactionRepository;
import com.visitor.repositories.StatsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("statsService")
public class StatsService { //implements StatsServiceInterface

    @Autowired
    StatsRepository statsRepository;

    @Autowired
    RealTimeTransactionRepository realTimeTransactionRepository;

    /**
     * Liste des employés
     * @return
     */
    public Integer countEmployee(){
        return statsRepository.countEmployee();
    }

    /**
     * load list current punchs
     * @return
     */
    public List<RealTimeTransaction> loadOnTimePunch(Date date){
        return realTimeTransactionRepository.findByPunchDate(date);
    }

    /*
    public List<Transactions> getListHearlyCheckIn(){
        return statsRepository.findAll(Sort.by("username").ascending());
    }*/
    
    /*@Override
    public List<Transactions> getListPunch() {
        
        return statsRepository.getListPunch();
    }*/
    
    

    /*public List<Transactions> findPunchByStatus(Date date,String status,Integer limit){
    
        return statsRepository.findPunchByStatus(date,status,limit);
       
    }*/

    /*public Integer countPunch(Date date,String status){
        return statsRepository.countPunch(date,status);
    }*/

    
}
