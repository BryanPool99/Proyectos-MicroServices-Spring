package com.bryan.UserRegistration_Service.controllers;

import com.bryan.UserRegistration_Service.model.dto.UserRequest;
import com.bryan.UserRegistration_Service.model.dto.UserResponse;
import com.bryan.UserRegistration_Service.model.entities.User;
import com.bryan.UserRegistration_Service.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    //listar usuarios
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUser();
    }
    //crear usuario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserRequest userRequest){
        return userService.saveUser(userRequest);
    }

}
