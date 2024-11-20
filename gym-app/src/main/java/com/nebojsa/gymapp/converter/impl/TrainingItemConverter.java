/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.AppDto;
import com.nebojsa.gymapp.dto.TrainingItemDto;
import com.nebojsa.gymapp.model.TrainingItem;
import java.awt.BorderLayout;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class TrainingItemConverter implements Converter<TrainingItem, TrainingItemDto>{
    
    private final ExerciseConverter exerciseConverter;

    public TrainingItemConverter(ExerciseConverter exerciseConverter) {
        this.exerciseConverter = exerciseConverter;
    }

    
    

    @Override
    public TrainingItem toEntity(TrainingItemDto dto) {
        return new TrainingItem(dto.getId(), null, exerciseConverter.toEntity(dto.getExercise()), dto.getNumOfReps());
//        return new TrainingItem(dto.getId(), dto.getTrainingSession(), dto.getExcercise(), dto.getNumOfReps());
    }
    


    @Override
    public TrainingItemDto toDto(TrainingItem entity) {
        TrainingItemDto dto =new TrainingItemDto(entity.getId(), exerciseConverter.toDto(entity.getExercise()), entity.getNumOfReps());
        return dto;
        
//        return new TrainingItemDto(entity.getId(), entity.getTrainingSession(), entity.getExcercise(), entity.getNumOfReps());
    }
    
}
