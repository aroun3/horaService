package com.visitor.controller.visitapp;

import com.visitor.entities.visitor.Coupling;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.repositories.UserRepository;
import com.visitor.services.visitor.CouplingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class CouplingController {

    private static final Logger logger = Logger.getLogger(CouplingController.class.getName());

    @Autowired
    private CouplingService couplingService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/coupling")
    public  List<Coupling> getAllCoupling(){
        return couplingService.getAll();
    }


    @PostMapping("/coupling")
    public ResponseEntity<?> saveCoupling(@RequestBody Coupling coupling){
        try {
            Coupling data = couplingService.add(coupling);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }
    @PutMapping("/coupling/{id}")
    public ResponseEntity<?> updateCoupling(@RequestBody Coupling coupling, @PathVariable Integer id){
        try {
            coupling.setId(id);
            Coupling data = couplingService.update(coupling);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }


    @GetMapping("/coupling/{id}")
    public  ResponseEntity<?> getCoupling(@PathVariable Integer id){
            try {
                Coupling coupling = couplingService.getOneById(id);
                return ResponseEntity.ok().body(new ApiResponse(true, coupling));
            }catch (Exception ex){
                return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


            }
    }

    @DeleteMapping("/coupling/{id}")
    public ResponseEntity<?> deleteCoupling(@PathVariable Integer id){
        try {
            couplingService.delete(id);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], null));
        }catch (Exception ex){
            System.out.println("Erreur de supp "+ex.getMessage() );
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], "Impossible de supprimer cette ligne !!!"));


        }
    }
    

}
