package com.visitor.controller;

import com.visitor.entities.Department;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.repositories.UserRepository;
import com.visitor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    private static final Logger logger = Logger.getLogger(DepartmentController.class.getName());

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/department")
    public  List<Department> getAllDepartment(){
        return departmentService.getAll();
    }


    @PostMapping("/department")
    public ResponseEntity<?> saveDepartment(@RequestBody Department department){
        try {
            Department data = departmentService.add(department);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }
    @PutMapping("/department/{id}")
    public ResponseEntity<?> updateDepartment(@RequestBody Department department, @PathVariable Integer id){
        try {
            department.setId(id);
            Department data = departmentService.update(department);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }


    @GetMapping("/department/{id}")
    public  ResponseEntity<?> getDepartment(@PathVariable Integer id){
            try {
                Department department = departmentService.getOneById(id);
                return ResponseEntity.ok().body(new ApiResponse(true, department));
            }catch (Exception ex){
                return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));


            }
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Integer id){
        try {
            departmentService.delete(id);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], null));
        }catch (Exception ex){
            System.out.println("Erreur de supp "+ex.getMessage() );
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], "Impossible de supprimer cette ligne !!!"));


        }
    }


}
