package com.visitor.controller;

import com.visitor.payload.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
    
    @GetMapping("/")
    public ResponseEntity<?> index(){
        return ResponseEntity.ok().body(new ApiResponse(true, "Vous etes sur la page d'accueil"));
    }
}
