/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.controller;

import com.nebojsa.gymapp.config.UserAuthProvider;
import com.nebojsa.gymapp.dto.CoachDto;
import com.nebojsa.gymapp.dto.LoginDto;
import com.nebojsa.gymapp.enums.Role;
import com.nebojsa.gymapp.model.Coach;
import com.nebojsa.gymapp.model.Gender;
import com.nebojsa.gymapp.security.response.AuthResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nebojsa.gymapp.service.CoachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.BadCredentialsException; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.crypto.password.PasswordEncoder; 

/**
 *
 * @author Admin
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class CoachController {
    
    private final CoachService coachService;
    private final UserAuthProvider userAuthProvider;
    
     @Autowired
    private PasswordEncoder passwordEncoder; 

    public CoachController(CoachService coachService, UserAuthProvider userAuthProvider) {
        this.coachService = coachService;
        this.userAuthProvider = userAuthProvider;
    }
   
    
    @PostMapping("/login") 
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) { 
        String username = loginDto.getUsername(); 
        String password = loginDto.getPassword(); 
  
        System.out.println(username+"-------"+password); 
  
        CoachDto coach = coachService.login(loginDto);
        System.out.println(coach);
//        System.out.println("evo me u loginu");
        coach.setToken(userAuthProvider.createToken(coach));
//        System.out.println(username+"-------"+password);
        System.out.println("Token created for user: " + coach.getUsername() + ": " +coach.getToken());
  
        return new ResponseEntity(coach,HttpStatus.OK); 
    }
    
    
    @GetMapping("/coaches")
//    @PreAuthorize("hasAuthority('OWNER')")
    List<CoachDto> getAllCoaches(){
        return coachService.findAll();
    }
    
    @PostMapping("/add-coach")
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<Object> createCoach(@RequestBody CoachDto coachDto){
        try {
            CoachDto newCoach = new CoachDto(coachDto.getFirstname(), coachDto.getLastname(), coachDto.getBirthday(), Role.EMPLOYEE, coachDto.getGender(), coachDto.getPhone(), coachDto.getUsername(), coachDto.getPassword());
            newCoach.setPassword(passwordEncoder.encode(coachDto.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED).body(coachService.save(newCoach));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    
    @GetMapping("/coach/{id}")
    Optional<CoachDto> getCoachById(@PathVariable Long id){
            return coachService.findById(id);

    }
    
//    @GetMapping("/coachesByName")
////    @PreAuthorize("hasAuthority('OWNER')")
//    List<CoachDto> getCoachesByName(@RequestBody String param){
//        System.out.println("Parametar je " + param);
//        return coachService.findByFullName(param);
//    }
    
    @PutMapping("/coach/{id}")
    public ResponseEntity<Object> updateCoach(@RequestBody CoachDto newCoach, @PathVariable Long id){
        System.out.println("TO UPDATE: " + newCoach);
//        return coachService.findById(id)
//                .map(coach ->{
//                    coach.setFirstname(newCoach.getFirstname());
//                    coach.setLastname(newCoach.getLastname());
//                    coach.setBirthday(newCoach.getBirthday());
//                    coach.setGender(newCoach.getGender());
//                    coach.setRole(newCoach.getRole());
//                    coach.setPhone(newCoach.getPhone());
//                    coach.setUsername(newCoach.getUsername());
//                    return coachService.save(coach);
//                });
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(coachService.save(newCoach));
            } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/coach/{id}")
    public ResponseEntity<Object> deleteCoach(@PathVariable Long id){
        try {
            coachService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return null;
    }
    
    
    }
