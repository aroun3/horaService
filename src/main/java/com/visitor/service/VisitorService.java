package com.visitor.service;

import com.visitor.entities.Visitor;
import org.springframework.web.multipart.MultipartFile;

public interface VisitorService extends BaseService<Visitor>{
    public Visitor addVisitor(Visitor visitor, MultipartFile multipartFile);
    public Visitor updateVisitor(Visitor visitor, MultipartFile multipartFile);
    public Integer countVisitor();
}
