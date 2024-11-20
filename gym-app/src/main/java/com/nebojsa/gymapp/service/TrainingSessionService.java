/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nebojsa.gymapp.service;

import com.nebojsa.gymapp.dto.TrainingSessionDto;
import com.nebojsa.gymapp.dto.TrainingSessionListDto;
import com.nebojsa.gymapp.dto.ViewTrainingSessionDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */

public interface TrainingSessionService {
    
    TrainingSessionDto save(TrainingSessionDto param);
    
    void delete(Long id);
    
    List<TrainingSessionListDto>findAll();
    
    Optional<ViewTrainingSessionDto> findById(Long number);
    
}
