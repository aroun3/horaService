package com.visitor.services.visitor;

import com.visitor.entities.User;
import com.visitor.entities.visitor.Nfc;
import com.visitor.entities.visitor.Visitor;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.repositories.VisitorRepository;
import com.visitor.repositories.visitor.NfcRepository;
import com.visitor.service_interfaces.VisitorServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("visitorService")
public class VisitorService implements VisitorServiceInterface {
    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    NfcService nfcService;

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
        Optional<Visitor> visitor = visitorRepository.findById(id);
        return visitor.get();
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

    @Override
    @Transactional
    public ResponseEntity<?> decoupleVisitor(Integer visitorId, String code) {
        try {
            Nfc nf = nfcService.findByNfcId(code);
            nf.setStatus(false);
            Nfc nfc = nfcService.update(nf);
            Visitor visitor = getOneById(visitorId);
            visitor.setStatus((short)2);
            visitor.setOutDate(new Date());
            Visitor vi = update(visitor);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], vi));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }


}
