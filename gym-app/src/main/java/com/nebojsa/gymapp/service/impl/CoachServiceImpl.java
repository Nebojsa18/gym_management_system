/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.service.impl;

import com.nebojsa.gymapp.converter.impl.CoachConverter;
import com.nebojsa.gymapp.dto.CoachDto;
import com.nebojsa.gymapp.dto.LoginDto;
import com.nebojsa.gymapp.exception.InvalidEntityException;
import com.nebojsa.gymapp.model.Coach;
import com.nebojsa.gymapp.repository.CoachRepository;
import com.nebojsa.gymapp.service.CoachService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CoachServiceImpl implements CoachService {
    
    private final CoachRepository coachRepository;
    private final CoachConverter coachConverter;
    private final PasswordEncoder passEncoder;

    public CoachServiceImpl(CoachRepository coachRepository, CoachConverter coachConverter, PasswordEncoder passEncoder) {
        this.coachRepository = coachRepository;
        this.coachConverter = coachConverter;
        this.passEncoder = passEncoder;
    }
    
    public CoachRepository getCoachRepository() {
        return coachRepository;
    }
    

    @Override
    public CoachDto save(CoachDto coach) {
        System.out.println(coach);
        
        Coach coachEntity = coachConverter.toEntity(coach);
        Optional<Coach> entity = coachRepository.findByUsername(coachEntity.getUsername());
            System.out.println("Evo ga coach "+ coachEntity);
            System.out.println("Evo ga coach "+ entity);
            
        
        if (entity==null && !entity.get().getId().equals(coachEntity.getId())){
//            if not equals, throw exception
            throw new InvalidEntityException("Coach with username '"+ coachEntity.getUsername() + "' already exists!");
        }
        
        return coachConverter.toDto(coachRepository.save(coachEntity));
        
    }

    @Override
    public void delete(Long id) {
        Optional<Coach> entity = coachRepository.findById(id);
        if(!entity.isPresent()){
            throw new InvalidEntityException("Coach doesn't exist!");
        }
        coachRepository.deleteById(id);
    }

    @Override
    public List<CoachDto> findAll() {
        List<Coach> coaches = coachRepository.findAll();
        return coaches.stream().map(entity -> {
            return coachConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<CoachDto> findById(Long number) {
        Optional<Coach> entity = coachRepository.findById(number);
            System.out.println(entity);
        if (entity.isPresent()){
//            return coachConverter.toDto(entity);
            return entity.map(coachConverter::toDto);
        }else{
            throw new InvalidEntityException("Invalid coach!");
            
        }
//        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Coach> coach = coachRepository.findByUsername(username);
        System.out.println("Coach by username:" + coach);
        
        if(coach==null) { 
            throw new UsernameNotFoundException("Coach not found with this username "+username); 
  
        } 
        
        System.out.println("Loaded coach: " + coach.get().getUsername()); 
        List<GrantedAuthority> authorities = new ArrayList<>(); 
        return new org.springframework.security.core.userdetails.User( 
                coach.get().getUsername(), 
                coach.get().getPassword(), 
                authorities); 
    
        
    }

    @Override
    public CoachDto findByUsername(String username) {
        Optional<Coach> coach = coachRepository.findByUsername(username);
        if(coach==null) { 
            throw new UsernameNotFoundException("Coach not found with this username "+username); 
  
        } 
                
        return coachConverter.toDto(coach.get());
    }

    @Override
    public CoachDto login(LoginDto loginDto) {
        Optional<Coach> coach = coachRepository.findByUsername(loginDto.getUsername());

        if(coach==null) { 
            throw new UsernameNotFoundException("Coach not found with this username "+loginDto.getUsername()); 
        } 
        
        if(passEncoder.matches(loginDto.getPassword(), coach.get().getPassword())){

            return coachConverter.toDto(coach.get());
        }

        throw new UsernameNotFoundException("Coach not found with this password ");
        
    }

//    

    
    
}
