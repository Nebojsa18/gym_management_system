/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nebojsa.gymapp.service;

import com.nebojsa.gymapp.dto.MemberDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public interface MemberService{
    
    MemberDto save(MemberDto memberDto);
//    
    void delete(Long id);
//    
    List<MemberDto> findAll();
//    
    Optional<MemberDto> findById(Long number);
    
//    CoachDto findByUsername(String username);
    
    
}
