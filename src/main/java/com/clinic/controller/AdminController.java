package com.clinic.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    
    // Endpoint pour accéder au niveau d'administration
    @GetMapping("/")
    public String helloAdminController(){
        return "Admin level access";
    }
}
