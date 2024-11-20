/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.controller;

import com.nebojsa.gymapp.dto.TrainingItemDto;
import com.nebojsa.gymapp.model.TrainingItem;
import com.nebojsa.gymapp.service.TrainingItemService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class TrainingItemController {
    
    private final TrainingItemService tiService;

    public TrainingItemController(TrainingItemService tiService) {
        this.tiService = tiService;
    }
    
//    @GetMapping("/items/{id}")
//    List<TrainingItemDto> getItemsFromSession(@PathVariable Long id){
//        return tiService.findBySessionId(id);
//    }
    
}
