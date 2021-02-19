package com.visitor.controller;

import com.visitor.entities.Role;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping(value="/role")
    public List<Role> getAll(){
        List<Role> roleList =roleService.getAll();
        return roleList;
    }

    @PostMapping("/role")
    public ResponseEntity<?>saveRole(@Valid @RequestBody Role role){
        try{
            Role data = roleService.add(role);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }

    @PutMapping("/role/{id}")
    public  ResponseEntity<?> updateRole(@Valid @RequestBody Role role, @PathVariable Integer id){
        try{
            role.setId(id);
            Role data = roleService.update(role);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }

    /*@GetMapping("/role/{id}")
    public ResponseEntity<?> getRole(@PathVariable Integer id){
        try{
            Role Role = RoleService.getOneById(id);
            return ResponseEntity.ok().body(new ApiResponse(true, Role));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }

    @DeleteMapping("/Role/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Integer id){
        try{
            RoleService.delete(id);
            return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_SUCCESS[1], null));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

        }
    }*/


}
