package com.bryan.Email_Service.controllers;

import com.bryan.Email_Service.model.dto.EmailRequest;
import com.bryan.Email_Service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        emailService.sendEmail(emailRequest);
        return ResponseEntity.ok("Correo electronico enviado correctamente");
    }
}
