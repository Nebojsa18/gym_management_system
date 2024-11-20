/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.TrainingSessionListDto;
import com.nebojsa.gymapp.model.TrainingItem;
import com.nebojsa.gymapp.model.TrainingSession;
import java.time.LocalTime;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class TrainingSessionListConverter implements Converter<TrainingSession, TrainingSessionListDto> {

    @Override
    public TrainingSession toEntity(TrainingSessionListDto dto) {
        return new TrainingSession(dto.getId(), dto.getDate(), dto.getTime(), null, null, null);
    }

    @Override
    public TrainingSessionListDto toDto(TrainingSession entity) {
        return new TrainingSessionListDto(entity.getId(), entity.getDate(), entity.getTime(),
                    entity.getCoach().getFirstname()+" "+ entity.getCoach().getLastname(), 
                    entity.getMember().getFirstname()+" "+entity.getMember().getLastname());
    }
    
}
