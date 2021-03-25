package com.visitor.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.visitor.entities.Phantom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PhantomRepository extends JpaRepository<Phantom,String>{
    /**
     * Total arrivée avant l'heure
     * @param status
     * @param date
     * @return
     */
    public Integer countByEarlyCheckinAndPunchDate(boolean status,Date date);

    /**
     * Total arrivé à l'heure
     * @param status
     * @param date
     * @return
     */
    public Integer countByOntimeCheckinAndPunchDate(boolean status,Date date);

    /**
     * Total arrivé en retard
     * @param status
     * @param date
     * @return
     */
    public Integer countByLateCheckinAndPunchDate(boolean status,Date date);


    /**
     * List des premier arrivé
     * @param status
     * @return
     */
    public List<Phantom> findByPunchDateAndCheckinStatusOrderByFirstPunchAsc(Date date,boolean status) ;

    /**
     * Liste des derniers arrive
     * @param status
     * @return
     */
    public List<Phantom> findByPunchDateAndCheckinStatusOrderByFirstPunchDesc(Date date,boolean status) ;
    /**
     * Liste des pointages de la @date courante
     * @param date
     * @return checkin_status / checkout_status
     */
    public List<Phantom> findByPunchDate(Date date);

    /**
     * Liste des arrivées
     * @param status
     * @return
     */
    
    public List<Phantom> countBy();
}
