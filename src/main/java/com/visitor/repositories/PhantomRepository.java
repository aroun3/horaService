package com.visitor.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.visitor.entities.Phantom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository Phantom : La necessité est de recuperer les données en temps réel et de les formaters sur le model de h_transaction
 */
@Repository
@Transactional
public interface PhantomRepository extends JpaRepository<Phantom,String>{

    /**
     * Pour les test de requetes
     * @return
     */
    @Query(value="SELECT * FROM h_phantom_view LIMIT 1",nativeQuery = true)
    public List<Phantom> test();


    /**
     * Total arrivée avant l'heure
     * @param status
     * @param date
     * @return
     */
    public Integer countByEarlyCheckinAndPunchDate(boolean status,Date date);

    /**
     * Liste des arrivées à l'heure
     * @param status
     * @param date
     * @return
     */
    public List<Phantom>  findByEarlyCheckinAndPunchDate(boolean status,Date date);

    /**
     * Total arrivé à l'heure
     * @param status
     * @param date
     * @return
     */
    public Integer countByOntimeCheckinAndPunchDate(boolean status,Date date);

    /**
     * Liste des arrivées à l'heure
     * @param status
     * @param date
     * @return
     */
    public List<Phantom> findByOntimeCheckinAndPunchDate(boolean status,Date date);
    /**
     * Total arrivé en retard
     * @param status
     * @param date
     * @return
     */
    public Integer countByLateCheckinAndPunchDate(boolean status,Date date);

    /**
     * Liste des arrivées en retards
     * @param status
     * @param date
     * @return
     */
    public List<Phantom> findByLateCheckinAndPunchDate(boolean status,Date date);


    /**
     * List des premiers arrivés
     * @param status
     * @return
     */
    @Query(value="SELECT * FROM h_phantom_view WHERE punch_date = :dateSelected AND checkin_status = :status ORDER BY first_punch ASC LIMIT 10",nativeQuery = true)
    public List<Phantom> loadPunchByDateAndStatusAsc(
        @Param("dateSelected") Date dateSelected,
        @Param("status") boolean status) ;


    /**
     * Liste des derniers arrivées
     * @param status
     * @return
     */
    @Query(value="SELECT * FROM h_phantom_view WHERE punch_date = :dateSelected AND checkin_status = :status ORDER BY first_punch DESC LIMIT 10",nativeQuery = true)
    public List<Phantom> loadPunchByDateAndStatusDesc(
        @Param("dateSelected") Date dateSelected,
        @Param("status") boolean status
    ) ;
    
    /**
     * Liste des pointages de la @date courante
     * @param date
     * @return checkin_status(arrivé) / checkout_status(depart)
     */
    public List<Phantom> findByPunchDate(Date date);


    @Query(value = "select * from h_phantom_view where emp_code = :code", nativeQuery = true)
	public List<Phantom> listEmpDesc(@Param("code") String emp_code);


}
