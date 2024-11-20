/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.controller;

import com.nebojsa.gymapp.dto.MembershipPaymentDto;
import com.nebojsa.gymapp.dto.TrainingSessionDto;
import com.nebojsa.gymapp.dto.TrainingSessionListDto;
import com.nebojsa.gymapp.dto.ViewTrainingSessionDto;
import com.nebojsa.gymapp.model.MembershipPayment;
import com.nebojsa.gymapp.service.MembershipPaymentService;
import com.nebojsa.gymapp.service.TrainingSessionService;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */

@RestController
@CrossOrigin("http://localhost:3000")
public class TrainingSessionController {
    
    private final TrainingSessionService tsService;

    private final MembershipPaymentService mpService;

    public TrainingSessionController(TrainingSessionService tsService, MembershipPaymentService mpService) {
        this.tsService = tsService;
        this.mpService = mpService;
    }

    
    @PostMapping("/add-session")
    public ResponseEntity<Object> newTrainingSession(@RequestBody TrainingSessionDto tsDto){
    try {
        MembershipPaymentDto membershipPayment = mpService.findLatestMembershipPaymentForMember(tsDto.getMember().getId());
        System.out.println("ID JE "+tsDto.getId());
        // if it doesn't exist
        if (membershipPayment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Member does not have a valid membership.");
        }
        
        // Check for expired membership
        if (new Date().after(membershipPayment.getValidUntil())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Membership has expired.");
        }
        
        // Check for remaining sessions
        if (membershipPayment.getRemainingSessions() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No remaining sessions in the membership.");
        }
        
        if(tsDto.getId()==null){
    //        Decreasing number of remaining sessions
            membershipPayment.setRemainingSessions(membershipPayment.getRemainingSessions() - 1);
            // Increasing number of used sessions
            membershipPayment.setUsedSessions(membershipPayment.getUsedSessions() + 1);
            // Updating membership payment obj
            mpService.save(membershipPayment);
        }
        
        // Dodajemo trening sesiju
        tsService.save(tsDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Training session successfully added.");
    
    } catch (Exception e) {
        System.out.println(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
    
    @PutMapping("/session/{id}")
    public ResponseEntity<Object> updateTrainingSession(@RequestBody TrainingSessionDto tsDto){
    try {
        MembershipPaymentDto membershipPayment = mpService.findLatestMembershipPaymentForMember(tsDto.getMember().getId());
        System.out.println("ID JE "+tsDto.getId());
        // if it doesn't exist
        if (membershipPayment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Member does not have a valid membership.");
        }
        
        // Check for expired membership
        if (new Date().after(membershipPayment.getValidUntil())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Membership has expired.");
        }
        
        // Proveravamo da li ima preostalih termina
        if (membershipPayment.getRemainingSessions() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No remaining sessions in the membership.");
        }
        // Dodajemo trening sesiju
        tsService.save(tsDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Training session successfully updated.");
    
    } catch (Exception e) {
        System.out.println(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
    
    @GetMapping("/sessions")
    List<TrainingSessionListDto> getTAllSessions(){
            return tsService.findAll();

    }
    
    @DeleteMapping("/session/{id}")
    public ResponseEntity<Object> deleteSession(@PathVariable Long id){
        try {
            tsService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return null;
    }
    
    @GetMapping("/session/{id}")
    Optional<ViewTrainingSessionDto> getTSessionById(@PathVariable Long id){
            return tsService.findById(id);

    }
    
}
