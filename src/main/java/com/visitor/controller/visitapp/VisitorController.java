package com.visitor.controller.visitapp;

import com.visitor.entities.User;
import com.visitor.entities.visitor.Visitor;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.repositories.UserRepository;
import com.visitor.services.visitor.VisitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class VisitorController {

    private static final Logger logger = Logger.getLogger(VisitorController.class.getName());

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/visitor")
    public  List<Visitor> getAllVisitor(){
        return visitorService.getAll();
    }


    @PostMapping("/visitor")
    public ResponseEntity<?> saveVisitor(@RequestBody Visitor visitor){
        try {
            visitor.setStatus((short)1);
            Visitor data = visitorService.add(visitor);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }
    
    @PutMapping("/visitor/{id}")
    public ResponseEntity<?> updateVisitor(@RequestPart("visitor") Visitor visitor,  @PathVariable Integer id){
        try {
            visitor.setId(id);
            Visitor data = visitorService.update(visitor);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }

    @GetMapping("/visitor/{id}")
    public  ResponseEntity<?> getVisitor(@PathVariable Integer id){
            try {
                Visitor visitor = visitorService.getOneById(id);
                return ResponseEntity.ok().body(new ApiResponse(true, visitor));
            }catch (Exception ex){
                return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


            }
    }

    @DeleteMapping("/visitor/{id}")
    public ResponseEntity<?> deleteVisitor(@PathVariable Integer id){
        try {
            visitorService.delete(id);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], null));
        }catch (Exception ex){
            System.out.println("Erreur de supp "+ex.getMessage() );
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], "Impossible de supprimer cette ligne !!!"));


        }
    }


    @GetMapping("/getTotalVisitor")
    public ResponseEntity<?> countVisitor(){
        try {
           Integer count = visitorService.countVisitor();
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], count));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


        }
    }

    /*@GetMapping("/getPersonals")
    public ResponseEntity<?> getListPersonals(){
        
    }*/
}
