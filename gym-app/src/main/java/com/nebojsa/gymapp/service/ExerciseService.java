/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.service;

import com.nebojsa.gymapp.dto.ExerciseDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public interface ExerciseService{
    ExerciseDto save(ExerciseDto param);
    
    void delete(Long id);
    
    List<ExerciseDto>findAll();
    
    Optional<ExerciseDto> findById(Long number);
}
