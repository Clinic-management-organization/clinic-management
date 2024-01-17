package com.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.clinic.dto.LoginResponseDTO;
import com.clinic.dto.RegistrationDTO;
import com.clinic.entity.ApplicationUser;
import com.clinic.metier.metierImpl.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    // Endpoint pour l'enregistrement d'un nouvel utilisateur
    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    // Endpoint pour la connexion d'un utilisateur
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
