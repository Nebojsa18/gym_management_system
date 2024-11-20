/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.config;

import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nebojsa.gymapp.dto.CoachDto;
import com.nebojsa.gymapp.service.CoachService;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


/**
 *
 * @author Admin
 */
@Component
public class UserAuthProvider {
    
    @Value("${security.jwt.token.secret-key:secret-value")
    private String secretKey;
    
    private final CoachService coachService;

    public UserAuthProvider(CoachService coachService) {
        this.coachService = coachService;
    }
    
    
    
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    
    public String createToken(CoachDto coach){
        Date now = new Date();
        Date validity = new Date(now.getTime()+ 3_600_000);
        
        return JWT.create()
                .withIssuer(coach.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("role", coach.getRole().name())
                .sign(Algorithm.HMAC256(secretKey));
    }
    
    public Authentication validateToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                .build();
        
        DecodedJWT decoded = verifier.verify(token);
        
        CoachDto coach = coachService.findByUsername(decoded.getIssuer());
        System.out.println("AUTORITET " + coach.getRole());
        return new UsernamePasswordAuthenticationToken(coach, null, Arrays.asList(coach.getRole()));
        
    }
    
}
