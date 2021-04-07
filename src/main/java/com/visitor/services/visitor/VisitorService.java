package com.visitor.services.visitor;

import com.visitor.entities.User;
import com.visitor.entities.visitor.Visitor;
import com.visitor.repositories.VisitorRepository;
import com.visitor.service_interfaces.VisitorServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service("visitorService")
public class VisitorService implements VisitorServiceInterface {
    @Autowired
    VisitorRepository visitorRepository;

    @Override
    public List<Visitor> getAll() {
        return visitorRepository.findAll();
    }

    @Override
    public Visitor add(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public Visitor update(Visitor visitor) {
        if(visitor.getId()==0){
            return visitorRepository.save(visitor);
        }else {
            return visitorRepository.saveAndFlush(visitor);
        }
    }

    @Override
    public Visitor getOneById(Integer id) {
        return visitorRepository.getOne(id);
    }

    @Override
    public void delete(Integer id) {
        visitorRepository.deleteById(id);
    }

    @Override
    public Visitor addVisitor(Visitor visitor, MultipartFile multipartFile) {
        Visitor vis = null;
        /*try {

            if(multipartFile.getSize() > 0){
                String fileArticleName = multipartFile.getOriginalFilename();
                byte[] bytesArticle = multipartFile.getBytes();
                visitor.setPhoto(bytesArticle);
                visitor.setPhotoName(fileArticleName);
            }
            vis.setStatus((short) 1);
            vis = add(visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return vis;
    }

    @Override
    public Visitor updateVisitor(Visitor visitor, MultipartFile multipartFile) {
        Visitor vis = null;
        /*try {

            if(multipartFile.getSize() > 0){
                String fileArticleName = multipartFile.getOriginalFilename();
                byte[] bytesArticle = multipartFile.getBytes();
                visitor.setPhoto(bytesArticle);
                visitor.setPhotoName(fileArticleName);
            }
            vis = update(visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return vis;
    }

    @Override
    public Integer countVisitor() {
        return visitorRepository.countVisitor();
    }

    @Override
    public List<Visitor> findByUser(User user) {
        return visitorRepository.findByUser(user);
    }

    @Override
    public List<Visitor> findByUserAndInDate(Integer userId) {

        return visitorRepository.findByUserAndInDate(userId);
    }
}
