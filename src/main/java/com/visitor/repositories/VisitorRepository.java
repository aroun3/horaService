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



    
}
