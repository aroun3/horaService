package com.visitor.controller.visitapp;

import com.visitor.entities.Employee;
import com.visitor.entities.User;
import com.visitor.entities.visitor.Nfc;
import com.visitor.entities.visitor.Visitor;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.payload.response.EmployeeResponse;
import com.visitor.payload.response.VisitorTotalResponse;
import com.visitor.repositories.UserRepository;
import com.visitor.services.EmployeeService;
import com.visitor.services.visitor.NfcService;
import com.visitor.services.visitor.VisitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class VisitorController {

    private static final Logger logger = Logger.getLogger(VisitorController.class.getName());

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NfcService nfcService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/visitors")
    public  List<Visitor> getAllVisitor(){
       /*List<Visitor> visitors = visitorService.getAll().stream().filter(p -> p.getFullName().equals("TINA")).collect(Collectors.toList());
        return visitors;*/
        return visitorService.getAll();
    }




    @PostMapping("/visitor")
    public ResponseEntity<?> saveVisitor(@RequestBody Visitor visitor,Principal principal){
            Optional<User> user = userRepository.findByUsername(principal.getName());
            return visitorService.addVisitors(visitor,user.get());
    }



    @GetMapping("/makeout/visitor/{code}")
    public ResponseEntity<?> decoupleVisitor(@PathVariable String code){
       return visitorService.decoupleVisitor(code);
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

    @GetMapping("/getVisitorByCurrentUser")
    public  ResponseEntity<?> getListVisitorByCurrentUser(Principal principal){
        try {
            Optional<User> user = userRepository.findByUsername(principal.getName());
            List<Visitor> visitor = visitorService.findByUserAndInDate(user.get().getId());
            return ResponseEntity.ok().body(new ApiResponse(true, visitor));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


        }
    }

       @GetMapping("/listCountVistor")
    public List<VisitorTotalResponse> getListCountVisitor(){
        return visitorService.getTotalVistor();

    }


    @GetMapping("/visitor/listEmployee")
    public ResponseEntity<?> getAllEmployee(){
        try {
            List<EmployeeResponse> employeeResponseList = employeeService.getAllEmployee();
            return ResponseEntity.ok().body(new ApiResponse(true, employeeResponseList));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));
        }
    }

    /*@GetMapping("/getPersonals")
    public ResponseEntity<?> getListPersonals(){
        
    }*/
}
