package com.visitor.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.visitor.entities.Phantom;
import com.visitor.entities.RealTimeTransaction;
import com.visitor.entities.Transactions;
import com.visitor.repositories.PhantomRepository;
import com.visitor.repositories.RealTimeTransactionRepository;
import com.visitor.repositories.StatsRepository;
import com.visitor.service_interfaces.StatsServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("statsService")
public class StatsService implements StatsServiceInterface{

    @Autowired
    StatsRepository statsRepository;

    @Autowired
    RealTimeTransactionRepository realTimeTransactionRepository;

    @Autowired
    PhantomRepository phantomRepository;
    
    public Integer countByEarlyCheckin(Date date){
        return phantomRepository.countByEarlyCheckinAndPunchDate(true,date);
    }

    public Integer countByOntimeCheckin(Date date){
        return phantomRepository.countByOntimeCheckinAndPunchDate(true,date);
    }

    public Integer countByLateCheckin(Date date){
        return phantomRepository.countByLateCheckinAndPunchDate(true,date);
    }

    public List<Phantom> findByPunchDateAndCheckinStatusOrderByFirstPunchAsc(Date date){
        return phantomRepository.findByPunchDateAndCheckinStatusOrderByFirstPunchAsc(date,true);
    }

    public List<Phantom> findByPunchDateAndCheckinStatusOrderByFirstPunchDesc(Date date){
        return phantomRepository.findByPunchDateAndCheckinStatusOrderByFirstPunchDesc(date,true);
    }

    @Override
    public List<Transactions> getListPunch() {
        
        return statsRepository.getListPunch();
    }
    
    public List<Transactions> findAll() {
        
        return statsRepository.findAll();
    }

    public List<Transactions> findPunchByStatus(Date date,String status,Integer limit){
    
        return statsRepository.findPunchByStatus(date,status,limit);
       
    }

    public List<Transactions> getListHearlyCheckIn(){
        return statsRepository.findAll(Sort.by("username").ascending());
    }

    /**
     * Nombre de pointage
     * @param date
     * @param status
     * @return Integer
     */
    public Integer countPunch(Date date,String status){
        return statsRepository.countPunch(date,status);
    }

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

    /**
     * Liste des pointages de la @date courante
     * @param date
     * @return checkin_status / checkout_status
     */
    public List<Phantom> listPunchByStatus(Date date){
        return phantomRepository.findByPunchDate(date);
    }

    public List<Phantom> findByPhantomCheckinStatusIn(){
        List<String> status = new ArrayList<>();
        status.add("1");
        status.add("2");
        status.add("3");

        return phantomRepository.findAll();
    }
}
