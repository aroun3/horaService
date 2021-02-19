package com.visitor.controller;

import com.visitor.entities.TypePiece;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.repositories.UserRepository;
import com.visitor.service.TypePieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class TypePieceController {

    private static final Logger logger = Logger.getLogger(TypePieceController.class.getName());

    @Autowired
    private TypePieceService typePieceService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/typePiece")
    public  List<TypePiece> getAllTypePiece(){
        return typePieceService.getAll();
    }


    @PostMapping("/typePiece")
    public ResponseEntity<?> saveTypePiece(@RequestBody TypePiece typePiece){
        try {
            TypePiece data = typePieceService.add(typePiece);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }
    @PutMapping("/typePiece/{id}")
    public ResponseEntity<?> updateTypePiece(@RequestBody TypePiece typePiece, @PathVariable Integer id){
        try {
            typePiece.setId(id);
            TypePiece data = typePieceService.update(typePiece);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }


    @GetMapping("/typePiece/{id}")
    public  ResponseEntity<?> getTypePiece(@PathVariable Integer id){
            try {
                TypePiece typePiece = typePieceService.getOneById(id);
                return ResponseEntity.ok().body(new ApiResponse(true, typePiece));
            }catch (Exception ex){
                return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


            }
    }

    @DeleteMapping("/typePiece/{id}")
    public ResponseEntity<?> deleteTypePiece(@PathVariable Integer id){
        try {
            typePieceService.delete(id);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], null));
        }catch (Exception ex){
            System.out.println("Erreur de supp "+ex.getMessage() );
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], "Impossible de supprimer cette ligne !!!"));


        }
    }


}
