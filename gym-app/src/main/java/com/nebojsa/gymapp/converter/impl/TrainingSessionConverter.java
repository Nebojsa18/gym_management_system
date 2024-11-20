/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.AppDto;
import com.nebojsa.gymapp.dto.TrainingItemDto;
import com.nebojsa.gymapp.dto.TrainingSessionDto;
import com.nebojsa.gymapp.model.Coach;
import com.nebojsa.gymapp.model.Member;
import com.nebojsa.gymapp.model.TrainingItem;
import com.nebojsa.gymapp.model.TrainingSession;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class TrainingSessionConverter implements Converter<TrainingSession, TrainingSessionDto>{
    
//    PROVERITI!!!!
    
    private final CoachConverter coachConverter;
    private final MemberConverter memberConverter;
    private final TrainingItemConverter trainingItemConverter;

//    public TrainingSessionConverter(TrainingItemConverter trainingItemConverter) {
////        this.coachConverter = coachConverter;
////        this.memberConverter = memberConverter;
//        this.trainingItemConverter = trainingItemConverter;
//    }

    public TrainingSessionConverter(CoachConverter coachConverter, MemberConverter memberConverter, TrainingItemConverter trainingItemConverter) {
        this.coachConverter = coachConverter;
        this.memberConverter = memberConverter;
        this.trainingItemConverter = trainingItemConverter;
    }
    
    

    
   

    @Override
    public TrainingSession toEntity(TrainingSessionDto dto) {
        
//        System.out.println("Coach dto from sessiondto" + dto.getCoach());
//        System.out.println("Member dto from sessiondto" + dto.getMember());
        
        List<TrainingItem> trainingItems = dto.getTrainingItems().stream()
                                                .map(trainingItemConverter::toEntity)
                                                .collect(Collectors.toList());
        
        return new TrainingSession(dto.getId(), dto.getDate(), dto.getTime(), coachConverter.toEntity(dto.getCoach()), memberConverter.toEntity(dto.getMember()),trainingItems);
    }
    
    public TrainingSession toEntityLazy(TrainingSessionDto dto) {
        return new TrainingSession(dto.getId(), dto.getDate(), dto.getTime(), null, null, null);
    }

    @Override
    public TrainingSessionDto toDto(TrainingSession entity) {
        
        List<TrainingItemDto> trainingItemsDto = entity.getTrainingItems().stream()
                                                .map(trainingItemConverter::toDto)
                                                .collect(Collectors.toList());
        
        return new TrainingSessionDto(entity.getId(), entity.getDate(), entity.getTime(), coachConverter.toDto(entity.getCoach()), memberConverter.toDto(entity.getMember()),trainingItemsDto);
    }
    
}
