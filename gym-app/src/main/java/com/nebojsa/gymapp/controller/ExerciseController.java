/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.controller;

import com.nebojsa.gymapp.dto.ExerciseDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nebojsa.gymapp.service.ExerciseService;

/**
 *
 * @author Admin
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class ExerciseController {
    
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }
    
    @PostMapping("/add-exercise")
    public ResponseEntity<Object> newExercise(@RequestBody ExerciseDto exerciseDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.save(exerciseDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    
    @GetMapping("/exercises")
    List<ExerciseDto> getAllExercises(){
        return exerciseService.findAll();
    }
    
}
