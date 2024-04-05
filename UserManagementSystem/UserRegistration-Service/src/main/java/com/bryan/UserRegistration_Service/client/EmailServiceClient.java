package com.bryan.UserRegistration_Service.client;

import com.bryan.Email_Service.model.dto.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Email-Service", url = "http://localhost:8081")
public interface EmailServiceClient {
    @PostMapping("/api/email/send")
    void sendEmail(@RequestBody EmailRequest emailRequest);
}
