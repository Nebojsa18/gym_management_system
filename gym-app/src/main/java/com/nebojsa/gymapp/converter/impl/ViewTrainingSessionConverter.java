/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.AppDto;
import com.nebojsa.gymapp.dto.TrainingItemDto;
import com.nebojsa.gymapp.dto.ViewTrainingSessionDto;
import com.nebojsa.gymapp.model.TrainingItem;
import com.nebojsa.gymapp.model.TrainingSession;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class ViewTrainingSessionConverter implements Converter<TrainingSession, ViewTrainingSessionDto> {

    public ViewTrainingSessionConverter(com.nebojsa.gymapp.converter.impl.TrainingItemConverter trainingItemConverter) {
        this.trainingItemConverter = trainingItemConverter;
    }
    
    private final TrainingItemConverter trainingItemConverter;
    
    @Override
    public TrainingSession toEntity(ViewTrainingSessionDto dto) {
        
        List<TrainingItem> trainingItems = dto.getTrainingItems().stream()
                                                .map(trainingItemConverter::toEntity)
                                                .collect(Collectors.toList());
        
        
        return new TrainingSession(dto.getId(), dto.getDate(), dto.getTime(), null, null, trainingItems);
    }

    @Override
    public ViewTrainingSessionDto toDto(TrainingSession entity) {
        
        List<TrainingItemDto> trainingItemsDto = entity.getTrainingItems().stream()
                                                .map(trainingItemConverter::toDto)
                                                .collect(Collectors.toList());
        
        
        return new ViewTrainingSessionDto(entity.getId(), entity.getDate(), entity.getTime(),
                    entity.getCoach().getFirstname()+" "+ entity.getCoach().getLastname(), 
                    entity.getMember().getFirstname()+" "+entity.getMember().getLastname(),
                    trainingItemsDto);
    }
    
}
