package com.bryan.Authentication_Service.security;

import com.bryan.Authentication_Service.model.entities.AuthUser;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @PostConstruct
    protected void init(){
        secret= Base64.getEncoder().encodeToString(secret.getBytes());
    }
    public String createToken(AuthUser authUser){
        Map<String,Object> claims=new HashMap<>();
        claims= Jwts.claims().setSubject()
    }
}
