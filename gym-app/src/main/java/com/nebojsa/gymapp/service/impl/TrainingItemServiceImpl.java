/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.service.impl;

import com.nebojsa.gymapp.converter.impl.TrainingItemConverter;
import com.nebojsa.gymapp.dto.TrainingItemDto;
import com.nebojsa.gymapp.model.TrainingItem;
import com.nebojsa.gymapp.repository.TrainingItemRepository;
import com.nebojsa.gymapp.service.TrainingItemService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class TrainingItemServiceImpl implements TrainingItemService{

    private final TrainingItemRepository trainingItemRepository;
    private final TrainingItemConverter trainingItemConverter;

    public TrainingItemServiceImpl(TrainingItemRepository trainingItemRepository, TrainingItemConverter trainingItemConverter) {
        this.trainingItemRepository = trainingItemRepository;
        this.trainingItemConverter = trainingItemConverter;
    }
    
    
    
    @Override
    public TrainingItemDto save(TrainingItemDto param) {
        System.out.println("service impl "+param);
        
        TrainingItem trainingItemEntity = trainingItemConverter.toEntity(param);
        
        return trainingItemConverter.toDto(trainingItemRepository.save(trainingItemEntity));
        
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TrainingItemDto> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
//    List<Member> members = memberRepository.findAll();
//        return members.stream().map(entity ->{
//        return memberConverter.toDto(entity);
//        }).collect(Collectors.toList());

    
    @Override
    public List<TrainingItemDto> findBySessionId(Long number) {
        List<TrainingItem> items= trainingItemRepository.findBySessionId(number);
            System.out.println("itemi: " +items);
        List<TrainingItemDto> lista = items.stream().map(entity -> {
        return trainingItemConverter.toDto(entity);
        }).collect(Collectors.toList());
        System.out.println("Lista " + lista);
        return lista;
    }

    @Override
    public Optional<TrainingItemDto> findById(Long number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
