package com.visitor.controller;

import com.visitor.entities.Hparams;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.payload.response.HparamResponse;
import com.visitor.repositories.HparamsRepository;
import com.visitor.services.HparamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HparamsController {
    @Autowired
    HparamsService hparamsService;
    @Autowired
    HparamsRepository hparamsRepository;
    @GetMapping("/hparams")
    public List<HparamResponse> getAllHparams(){
        //return  hparamsService.getAll();
        return hparamsRepository.getAllHparam();
    }

    @PostMapping("/hparams")
    public ResponseEntity<?> addHparams(@RequestBody Hparams hparams, HttpServletRequest request){

        try {
            Hparams hp = hparamsService.add(hparams);
            return ResponseEntity.ok().body(new ApiResponse(true,  AppConstants.STATUS_CODE_SUCCESS[1], hp));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


        }
    }

    @PutMapping("/hparams/{id}")
    public ResponseEntity<?> updateHparams(@RequestBody Hparams hparams,@PathVariable Integer id){
        try {
            hparams.setId(id);
            Hparams hp = hparamsService.update(hparams);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], hp));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


        }
    }

    @GetMapping("/hparams/{id}")
    public Hparams getById(@PathVariable Integer id){
        return hparamsService.getOneById(id);
    }


    @DeleteMapping("/hparams/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        try{
         hparamsService.delete(id);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], null));
    }catch (Exception ex){
        return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


    }
    }

}
