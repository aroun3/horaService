package com.visitor.services;

import java.util.Date;
import java.util.List;

import com.visitor.entities.Phantom;
import com.visitor.repositories.PhantomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("phantomService")
public class PhantomService {

    @Autowired
    PhantomRepository phantomRepository;
    
    /**
     * Total pointage des arrivées avant l'heure
     * @param date
     * @return
     */
    public Integer countByEarlyCheckinAndPunchDate(Date date){
        return phantomRepository.countByEarlyCheckinAndPunchDate(true,date);
    }

    /**
     * Total pointage des arrivées à l'heure
     * @param date
     * @return
     */
    public Integer countByOntimeCheckinAndPunchDate(Date date){
        return phantomRepository.countByOntimeCheckinAndPunchDate(true,date);
    }

    /**
     * Total pointage en retard par date
     * @param date
     * @return
     */
    public Integer countByLateCheckinAndPunchDate(Date date){
        return phantomRepository.countByLateCheckinAndPunchDate(true,date);
    }

    /**
     * Liste des pontages par date et checkin_status ordonner par croissante (Liste des prmiers arrivées)
     * @param date
     * @return
     */
    public List<Phantom> findByPunchDateAndCheckinStatusOrderByFirstPunchAsc(Date date){
        return phantomRepository.findByPunchDateAndCheckinStatusOrderByFirstPunchAsc(date,true);
    }

    /**
     * Liste des pontages par date et checkin_status ordonner par decroissance (Liste des derniers arrivées)
     * @param date
     * @return
     */

    public List<Phantom> findByPunchDateAndCheckinStatusOrderByFirstPunchDesc(Date date){
        return phantomRepository.findByPunchDateAndCheckinStatusOrderByFirstPunchDesc(date,true);
    }

    /**
     * Liste des pointages de la @date courante
     * @param date
     * @return checkin_status(arrivé) / checkout_status(depart)
     */
    public List<Phantom> listPunchByPunchDate(Date date){
        return phantomRepository.findByPunchDate(date);
    }


    /*public List<Phantom> findByPhantomCheckinStatusIn(){
        List<String> status = new ArrayList<>();
        status.add("1");
        status.add("2");
        status.add("3");

        return phantomRepository.findAll();
    }*/
}
