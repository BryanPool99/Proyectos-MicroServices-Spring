package com.bryan.UserRegistration_Service.controllers;

import com.bryan.UserRegistration_Service.exceptions.EmailAlreadyExistsException;
import com.bryan.UserRegistration_Service.model.dto.ErrorResponse;
import com.bryan.UserRegistration_Service.model.dto.UserRequest;
import com.bryan.UserRegistration_Service.model.dto.UserResponse;
import com.bryan.UserRegistration_Service.model.entities.User;
import com.bryan.UserRegistration_Service.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> saveUser(@RequestBody UserRequest userRequest){
        try {
            User newUser = userService.saveUser(userRequest);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException ex) {
            // Crear un objeto ErrorResponse con el mensaje de error
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
            // Devolver la respuesta personalizada con el objeto ErrorResponse y el código de estado HTTP 400 (Bad Request)
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


}
