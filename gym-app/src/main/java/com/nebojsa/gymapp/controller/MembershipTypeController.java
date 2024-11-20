/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.controller;

import com.nebojsa.gymapp.dto.MembershipTypeDto;
import com.nebojsa.gymapp.service.MembershipTypeService;
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
public class MembershipTypeController {
    
    private final MembershipTypeService mtService;

    public MembershipTypeController(MembershipTypeService mtService) {
        this.mtService = mtService;
    }
    
    @GetMapping("/membership-types")
    List<MembershipTypeDto> getAllMembershipTypes(){
        return mtService.findAll();
    }
    
    @PostMapping("/add-membership-type")
    public ResponseEntity<Object> newType(@RequestBody MembershipTypeDto mtDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mtService.save(mtDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping("/membership-type/{id}")
    Optional<MembershipTypeDto> getMTypeById(@PathVariable Long id){
        return mtService.findById(id);
    }
    
    @PutMapping("/membership-type/{id}")
    Optional<MembershipTypeDto> updateType(@RequestBody MembershipTypeDto newType, @PathVariable Long id){
        System.out.println("asd" + newType);
        return mtService.findById(id)
                .map(mType ->{
                    mType.setName(newType.getName());
                    mType.setPrice(newType.getPrice());
                    mType.setDescription(newType.getDescription());
                    return mtService.save(mType);
                });
    }
    
    
    @DeleteMapping("/membership-type/{id}")
    public ResponseEntity<Object> deleteType(@PathVariable Long id){
        try {
            mtService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return null;
    }
    
}
