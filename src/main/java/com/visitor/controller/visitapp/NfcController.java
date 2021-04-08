package com.visitor.controller.visitapp;

import com.visitor.entities.visitor.Nfc;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.repositories.UserRepository;
import com.visitor.services.visitor.NfcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class NfcController {

    private static final Logger logger = Logger.getLogger(NfcController.class.getName());

    @Autowired
    private NfcService NfcService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/nfc")
    public  List<Nfc> getAllNfc(){
        return NfcService.getAll();
    }


    @PostMapping("/nfc")
    public ResponseEntity<?> saveNfc(@RequestBody Nfc Nfc){
        try {
            Nfc data = NfcService.add(Nfc);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }
    @PutMapping("/nfc/{id}")
    public ResponseEntity<?> updateNfc(@RequestBody Nfc Nfc, @PathVariable Integer id){
        try {
            Nfc.setId(id);
            Nfc data = NfcService.update(Nfc);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }


    @GetMapping("/nfc/{id}")
    public  ResponseEntity<?> getNfc(@PathVariable Integer id){
            try {
                Nfc Nfc = NfcService.getOneById(id);
                return ResponseEntity.ok().body(new ApiResponse(true, Nfc));
            }catch (Exception ex){
                return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


            }
    }

    @GetMapping("/nfc/{nfcId}")
    public  ResponseEntity<?> getStatusNfc(@PathVariable String nfcId){
        try {
            Nfc Nfc = NfcService.findByNfcId(nfcId);
            return ResponseEntity.ok().body(new ApiResponse(true, Nfc.getStatus()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


        }
    }

    @DeleteMapping("/nfc/{id}")
    public ResponseEntity<?> deleteNfc(@PathVariable Integer id){
        try {
            NfcService.delete(id);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], null));
        }catch (Exception ex){
            System.out.println("Erreur de supp "+ex.getMessage() );
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], "Impossible de supprimer cette ligne !!!"));


        }
    }
    

}
