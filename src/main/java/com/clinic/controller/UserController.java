package com.clinic.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling user-related operations.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    /**
     * Handles GET requests for the root endpoint ("/user/").
     *
     * @return A welcome message indicating the user access level.
     */
    @GetMapping("/")
    public String helloUserController(){
        return "User access level";
    }
}




