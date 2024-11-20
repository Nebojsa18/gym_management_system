/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.service.impl;

import com.nebojsa.gymapp.converter.impl.ExerciseConverter;
import com.nebojsa.gymapp.dto.ExerciseDto;
import com.nebojsa.gymapp.exception.InvalidEntityException;
import com.nebojsa.gymapp.model.Exercise;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.nebojsa.gymapp.repository.ExerciseRepository;
import com.nebojsa.gymapp.service.ExerciseService;

/**
 *
 * @author Admin
 */

@Service
public class ExerciseServiceImpl implements ExerciseService{

    private final ExerciseRepository exerciseRepository;
    private final ExerciseConverter exerciseConverter;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseConverter exerciseConverter) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseConverter = exerciseConverter;
    }

    public ExerciseRepository getExerciseRepository() {
        return exerciseRepository;
    }
    
    
    
    @Override
    public ExerciseDto save(ExerciseDto exerciseDto) {
        
        System.out.println("Exercise DTO: "+exerciseDto);
        
        Exercise exerciseEntity = exerciseConverter.toEntity(exerciseDto);
//        Optional<Excercise> entity = excerciseRepository.findById(excerciseEntity.getId());
//            System.out.println("Evo ga excercise"+ excerciseEntity);
        
//        if (entity.isPresent() && !entity.get().getId().equals(excerciseEntity.getId())){
////            if not equals, throw exception
//            throw new InvalidEntityException("Excercise '"+ excerciseEntity.getExcerciseName()+ "' already exists!");
//        }
//        
        return exerciseConverter.toDto(exerciseRepository.save(exerciseEntity));
        
        
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ExerciseDto> findAll() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream().map(entity ->{
        return exerciseConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<ExerciseDto> findById(Long number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
