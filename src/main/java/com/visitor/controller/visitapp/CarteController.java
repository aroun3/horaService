package com.visitor.controller.visitapp;

import com.visitor.entities.visitor.Carte;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.repositories.UserRepository;
import com.visitor.services.CarteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class CarteController {

    private static final Logger logger = Logger.getLogger(CarteController.class.getName());

    @Autowired
    private CarteService carteService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/carte")
    public  List<Carte> getAllCarte(){
        return carteService.getAll();
    }


    @PostMapping("/carte")
    public ResponseEntity<?> saveCarte(@RequestBody Carte carte){
        try {
            Carte data = carteService.add(carte);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }
    @PutMapping("/carte/{id}")
    public ResponseEntity<?> updateCarte(@RequestBody Carte carte, @PathVariable Integer id){
        try {
            carte.setId(id);
            Carte data = carteService.update(carte);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }


    @GetMapping("/carte/{id}")
    public  ResponseEntity<?> getCarte(@PathVariable Integer id){
            try {
                Carte carte = carteService.getOneById(id);
                return ResponseEntity.ok().body(new ApiResponse(true, carte));
            }catch (Exception ex){
                return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


            }
    }

    @DeleteMapping("/carte/{id}")
    public ResponseEntity<?> deleteCarte(@PathVariable Integer id){
        try {
            carteService.delete(id);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], null));
        }catch (Exception ex){
            System.out.println("Erreur de supp "+ex.getMessage() );
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], "Impossible de supprimer cette ligne !!!"));


        }
    }


}
