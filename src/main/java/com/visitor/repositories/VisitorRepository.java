package com.visitor.repositories;

import com.visitor.entities.User;
import com.visitor.entities.visitor.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    public Optional<Visitor> findById(Integer id);
    public List<Visitor> findByUser(User user);

    @Query(value ="SELECT * FROM h_visitors v\n" +
            "WHERE v.user_id = :userId \n" +
            "AND EXTRACT(YEAR from v.in_date) = EXTRACT(YEAR from now())\n" +
            "AND EXTRACT(MONTH from v.in_date) = EXTRACT(MONTH from now())\n" +
            "AND EXTRACT(DAY from v.in_date) = EXTRACT(DAY from now())", nativeQuery = true)
    public  List<Visitor> findByUserAndInDate(@Param("userId") Integer userId);

    @Query(value = "SELECT COUNT(*) FROM article",nativeQuery = true)
    public Integer countVisitor();

    @Query(value = "SELECT COUNT(v.id),(SELECT COUNT(*) FROM h_visitors v \n" +
            " WHERE v.by_appointment ='0') AS srdv,(SELECT COUNT(*) FROM h_visitors v \n" +
            " WHERE v.by_appointment ='1') AS rdv, (SELECT count(v.id) FROM h_visitors v\n" +
            " WHERE EXTRACT(YEAR from v.in_date) = EXTRACT(YEAR from now())\n" +
            " AND EXTRACT(MONTH from v.in_date) = EXTRACT(MONTH from now())\n" +
            " AND EXTRACT(DAY from v.in_date) = EXTRACT(DAY from now()))AS current_visitor \n"+
            " FROM h_visitors v", nativeQuery = true)
    public List<Object[]> getTotalVistor();


    @Query(value = "SELECT * FROM h_visitors v WHERE v.nfc_ref= :nfcRef AND v.status is true", nativeQuery = true)
    public Visitor findVisitorByNfcRef(String nfcRef);

    
}
