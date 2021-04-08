package com.visitor.service_interfaces;

import com.visitor.entities.User;
import com.visitor.entities.visitor.Visitor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VisitorServiceInterface extends BaseServiceInterface<Visitor>{
    public Visitor addVisitor(Visitor visitor, MultipartFile multipartFile);
    public Visitor updateVisitor(Visitor visitor, MultipartFile multipartFile);
    public Integer countVisitor();
    public List<Visitor> findByUser(User user);
    public  List<Visitor> findByUserAndInDate(Integer userId);
    ResponseEntity<?> decoupleVisitor(Integer visitorId, String code);
}
