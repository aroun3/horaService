package com.visitor.controller.visitapp;

import com.visitor.entities.visitor.Nfc;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.payload.response.NfcResponse;
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
    private NfcService nfcService;

    @GetMapping("/nfc")
    public  List<Nfc> getAllNfc(){
        return nfcService.getAll();
    }


    @PostMapping("/nfc")
    public ResponseEntity<?> saveNfc(@RequestBody Nfc nfc){
        try {
            Nfc data = nfcService.add(nfc);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }
    @PutMapping("/nfc/{id}")
    public ResponseEntity<?> updateNfc(@RequestBody Nfc nfc, @PathVariable Integer id){
        try {
            nfc.setId(id);
            Nfc data = nfcService.update(nfc);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }


    @GetMapping("/nfc/{id}")
    public  ResponseEntity<?> getNfc(@PathVariable Integer id){
            try {
                Nfc nfc = nfcService.getOneById(id);
                if(nfc != null) {
                return ResponseEntity.ok().body(new ApiResponse(true, nfc));
                }else{
                    return ResponseEntity.ok().body(new ApiResponse(true, null));
                }
            }catch (Exception ex){
                return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


            }
    }

    @GetMapping("/nfc/status/{nfcId}")
    public  ResponseEntity<?> getStatusNfc(@PathVariable String nfcId){
        try {
            Nfc nfc = nfcService.findByNfcId(nfcId);
            if(nfc != null) {
                NfcResponse nfcResponse = new NfcResponse();
                nfcResponse.setNfcId(nfc.getNfcId());
                nfcResponse.setNfcRef(nfc.getNfcRef());
                nfcResponse.setStatus(nfc.getStatus());
                return ResponseEntity.ok().body(new ApiResponse(true, nfcResponse));
            }else{
                return ResponseEntity.ok().body(new ApiResponse(true, null));
            }
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


        }
    }

    @DeleteMapping("/nfc/{id}")
    public ResponseEntity<?> deleteNfc(@PathVariable Integer id){
        try {
            nfcService.delete(id);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], null));
        }catch (Exception ex){
            System.out.println("Erreur de supp "+ex.getMessage() );
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], "Impossible de supprimer cette ligne !!!"));


        }
    }
    

}
