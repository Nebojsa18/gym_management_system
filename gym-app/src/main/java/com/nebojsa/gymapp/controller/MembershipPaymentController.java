/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.controller;

import com.nebojsa.gymapp.dto.MembershipPaymentDto;
import com.nebojsa.gymapp.service.MembershipPaymentService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class MembershipPaymentController {
    
    private final MembershipPaymentService mpService;

    public MembershipPaymentController(MembershipPaymentService mpService) {
        this.mpService = mpService;
    }
    
    @PostMapping("/add-payment")
    public ResponseEntity<Object> newMembershipPayment(@RequestBody MembershipPaymentDto mpDto){
        try {
//            System.out.println(tsDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(mpService.save(mpDto));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    
     @GetMapping("/payments")
    List<MembershipPaymentDto> getAllPayments(){
            return mpService.findAll();

    }
    
    @GetMapping("member/{id}/membership-payments")
    List<MembershipPaymentDto> getAllPaymentsFromMember(@PathVariable Long id){
            return mpService.findMembershipsByMemberId(id);

    }
    
    
    
    
}
