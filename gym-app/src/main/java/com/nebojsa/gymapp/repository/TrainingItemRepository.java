/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nebojsa.gymapp.repository;

import com.nebojsa.gymapp.model.TrainingItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Admin
 */
public interface TrainingItemRepository extends JpaRepository<TrainingItem, Long>{
    
    @Query("SELECT ti FROM TrainingItem ti WHERE ti.trainingSession.id= ?1")
    List<TrainingItem> findBySessionId(Long id);
    
}
