package com.visitor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.visitor.entities.Transactions;
@Repository
public interface StatsRepository extends JpaRepository<Transactions, Integer>{
    
    /*@Query(value="SELECT * FROM h_transactions WHERE status = ?1",nativeQuery = true)
    public List<Transactions> getListPunch();
    
    @Query(value="SELECT * FROM h_transactions as ht WHERE ht.date_trans = :dateSelected AND ht.status =:status LIMIT :limit ",nativeQuery = true)
    public List<Transactions> findPunchByStatus(
        @Param("dateSelected") Date dateSelected,
        @Param("status") String status,
        @Param("limit") Integer limit
        );

    @Query(value="SELECT COUNT(*) FROM h_transactions as ht WHERE ht.date_trans = :dateSelected AND ht.status =:status",nativeQuery = true)
    public Integer countPunch(
        @Param("dateSelected") Date dateSelected,
        @Param("status") String status
        );*/

    /**
     * nombre des employé
     * @return
     */
    @Query(value="SELECT COUNT(*) FROM personnel_employee",nativeQuery = true)
    public Integer countEmployee();
    
}
