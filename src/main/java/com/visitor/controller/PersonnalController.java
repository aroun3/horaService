package com.visitor.controller;

import com.visitor.entities.Personnal;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.repositories.UserRepository;
import com.visitor.service.PersonnalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class PersonnalController {

    private static final Logger logger = Logger.getLogger(PersonnalController.class.getName());

    @Autowired
    private PersonnalService personnalService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/personnal")
    public  List<Personnal> getAllPersonnal(){
        return personnalService.getAll();
    }


    @PostMapping("/personnal")
    public ResponseEntity<?> savePersonnal(@RequestBody Personnal personnal){
        try {
            Personnal data = personnalService.add(personnal);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }
    @PutMapping("/personnal/{id}")
    public ResponseEntity<?> updatePersonnal(@RequestBody Personnal personnal, @PathVariable Integer id){
        try {
            personnal.setId(id);
            Personnal data = personnalService.update(personnal);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }


    @GetMapping("/personnal/{id}")
    public  ResponseEntity<?> getPersonnal(@PathVariable Integer id){
            try {
                Personnal personnal = personnalService.getOneById(id);
                return ResponseEntity.ok().body(new ApiResponse(true, personnal));
            }catch (Exception ex){
                return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


            }
    }

    @DeleteMapping("/personnal/{id}")
    public ResponseEntity<?> deletePersonnal(@PathVariable Integer id){
        try {
            personnalService.delete(id);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], null));
        }catch (Exception ex){
            System.out.println("Erreur de supp "+ex.getMessage() );
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], "Impossible de supprimer cette ligne !!!"));


        }
    }


}
