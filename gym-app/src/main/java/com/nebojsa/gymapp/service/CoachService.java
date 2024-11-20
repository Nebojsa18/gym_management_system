/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.service;

import com.nebojsa.gymapp.dto.CoachDto;
import com.nebojsa.gymapp.dto.LoginDto;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface CoachService extends UserDetailsService{
    
    CoachDto save(CoachDto coachDto);
//    
    void delete(Long id);
//    
    List<CoachDto> findAll();
//    
    Optional<CoachDto> findById(Long number);
    
    CoachDto findByUsername(String username);
    
    CoachDto login(LoginDto loginDto);
    
    
//    List<CoachDto> findByFullName(String param);
}
