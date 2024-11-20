/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nebojsa.gymapp.service;

import com.nebojsa.gymapp.dto.TrainingItemDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public interface TrainingItemService{
   
    TrainingItemDto save(TrainingItemDto param);
    
    void delete(Long id);
    
    List<TrainingItemDto>findAll();
    
    Optional<TrainingItemDto> findById(Long number);
    
    public List<TrainingItemDto> findBySessionId(Long number);
}
