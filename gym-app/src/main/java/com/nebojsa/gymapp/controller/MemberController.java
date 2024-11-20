/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.controller;

import com.nebojsa.gymapp.dto.MemberDto;
import com.nebojsa.gymapp.service.MemberService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
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
public class MemberController {
    
     private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    List<MemberDto> getAllMembers(){
        return memberService.findAll();
    }
    
    @GetMapping("/member/{id}")
    Optional<MemberDto> getMemberById(@PathVariable Long id){
            return memberService.findById(id);

    }
    
    @PostMapping("/add-member")
    public ResponseEntity<Object> newMember(@RequestBody MemberDto memberDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(memberDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    
    @PutMapping("/member/{id}")
    Optional<MemberDto> updateMember(@RequestBody MemberDto newMember, @PathVariable Long id){
        return memberService.findById(id)
                .map(member ->{
                    member.setFirstname(newMember.getFirstname());
                    member.setLastname(newMember.getLastname());
                    member.setBirthday(newMember.getBirthday());
                    member.setGender(newMember.getGender());
                    member.setPhone(newMember.getPhone());
                    member.setEmail(newMember.getEmail());
                    return memberService.save(member);
                });
    }
    
    @DeleteMapping("/member/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable Long id){
        try {
            memberService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return null;
    }
    
    
}
