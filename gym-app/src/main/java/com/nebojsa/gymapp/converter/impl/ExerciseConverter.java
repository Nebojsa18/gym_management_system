/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.AppDto;
import com.nebojsa.gymapp.dto.ExerciseDto;
import com.nebojsa.gymapp.model.Exercise;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class ExerciseConverter implements Converter<Exercise, ExerciseDto>{

    @Override
    public Exercise toEntity(ExerciseDto dto) {
        
        return new Exercise(dto.getId(), dto.getExerciseName());
    }

    @Override
    public ExerciseDto toDto(Exercise entity) {
        ExerciseDto dto = new ExerciseDto(entity.getId(), entity.getExerciseName());
        return dto;
    }
    
}
