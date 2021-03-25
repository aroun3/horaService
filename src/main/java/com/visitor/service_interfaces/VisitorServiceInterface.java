package com.visitor.service_interfaces;

import com.visitor.entities.visitor.Visitor;
import org.springframework.web.multipart.MultipartFile;

public interface VisitorServiceInterface extends BaseServiceInterface<Visitor>{
    public Visitor addVisitor(Visitor visitor, MultipartFile multipartFile);
    public Visitor updateVisitor(Visitor visitor, MultipartFile multipartFile);
    public Integer countVisitor();
}
