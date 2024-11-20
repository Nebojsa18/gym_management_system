/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.service.impl;

import com.nebojsa.gymapp.converter.impl.TrainingSessionConverter;
import com.nebojsa.gymapp.converter.impl.TrainingSessionListConverter;
import com.nebojsa.gymapp.converter.impl.ViewTrainingSessionConverter;
import com.nebojsa.gymapp.dto.TrainingSessionDto;
import com.nebojsa.gymapp.dto.TrainingSessionListDto;
import com.nebojsa.gymapp.dto.ViewTrainingSessionDto;
import com.nebojsa.gymapp.exception.InvalidEntityException;
import com.nebojsa.gymapp.model.TrainingItem;
import com.nebojsa.gymapp.model.TrainingSession;
import com.nebojsa.gymapp.repository.TrainingSessionRepository;
import com.nebojsa.gymapp.service.TrainingSessionService;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class TrainingSessionServiceImpl implements TrainingSessionService{

    private final TrainingSessionRepository tsRepository;
    private final TrainingSessionConverter tsConverter;
    private final TrainingSessionListConverter tslConverter;
    private final ViewTrainingSessionConverter vtsConverter;
    


    public TrainingSessionServiceImpl(TrainingSessionRepository tsRepository, TrainingSessionConverter tsConverter, TrainingSessionListConverter tslConverter, ViewTrainingSessionConverter vtsConverter) {
        this.tsRepository = tsRepository;
        this.tsConverter = tsConverter;
        this.tslConverter = tslConverter;
        this.vtsConverter = vtsConverter;
    }
    
    
    
    @Override
    public TrainingSessionDto save(TrainingSessionDto param) {
        
        System.out.println("Evo TrainingSessionDTO: " + param);
        
        TrainingSession tsEntity = tsConverter.toEntity(param);
        
        Date newTrainingDate = tsEntity.getDate();
        LocalTime newTrainingTime = tsEntity.getTime();
        
        for (TrainingItem trainingItem : tsEntity.getTrainingItems()) {
//            System.out.println("Item update?" + tsEntity);
            trainingItem.setTrainingSession(tsEntity);
        }
        
        
        return tsConverter.toDto(tsRepository.save(tsEntity));
        
    }

    @Override
    public void delete(Long id) {
        Optional<TrainingSession> entity = tsRepository.findById(id);
        if(!entity.isPresent()){
            throw new InvalidEntityException("Training Session doesn't exist!");
        }
        tsRepository.deleteById(id);
    }

    @Override
    public List<TrainingSessionListDto> findAll() {
        List<TrainingSession> tSessions = tsRepository.findAll();
        return tSessions.stream().map(entity ->{
        return tslConverter.toDto(entity);
        }).collect(Collectors.toList());
    }
    
    

    @Override
    public Optional<ViewTrainingSessionDto> findById(Long number) {
        Optional<TrainingSession> entity = tsRepository.findById(number);
            System.out.println(entity);
        if (entity.isPresent()){
//            return coachConverter.toDto(entity);
            return entity.map(vtsConverter::toDto);
        }else{
            throw new InvalidEntityException("Invalid training session!");
            
        }
    }
    
}
