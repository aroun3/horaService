package com.visitor.controller.visitapp;

import com.visitor.entities.visitor.Nfc;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.payload.response.NfcResponse;
import com.visitor.repositories.UserRepository;
import com.visitor.services.visitor.NfcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> saveNfc(@RequestBody Nfc nfc){
        try {
            Nfc data = NfcService.add(nfc);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }
    @PutMapping("/nfc/{id}")
    public ResponseEntity<?> updateNfc(@RequestBody Nfc nfc, @PathVariable Integer id){
        try {
            nfc.setId(id);
            Nfc data = NfcService.update(nfc);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }


    @GetMapping("/nfc/{id}")
    public  ResponseEntity<?> getNfc(@PathVariable Integer id){
            try {
                Nfc nfc = NfcService.getOneById(id);
                if(nfc != null) {
                return ResponseEntity.ok().body(new ApiResponse(true, nfc));
                }else{
                    throw  new IllegalStateException("La carte nfc n'existe pas");
                }
            }catch (Exception ex){
                return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


            }
    }

    @GetMapping("/nfc/status/{nfcId}")
    public  ResponseEntity<?> getStatusNfc(@PathVariable String nfcId){
        try {
            Nfc nfc = NfcService.findByNfcId(nfcId);
            if(nfc != null) {
                NfcResponse nfcResponse = new NfcResponse();
                nfcResponse.setNfcId(nfc.getNfcId());
                nfcResponse.setStatus(nfc.getStatus());
                return ResponseEntity.ok().body(new ApiResponse(true, nfcResponse));
            }else{
                throw  new IllegalStateException("La carte nfc n'existe pas");
            }
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
