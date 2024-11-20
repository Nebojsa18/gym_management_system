/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nebojsa.gymapp.converter;

import com.nebojsa.gymapp.dto.AppDto;
import com.nebojsa.gymapp.model.AppEntity;

/**
 *
 * @author Admin
 */
public interface Converter <E extends AppEntity, D extends AppDto>{
    
    E toEntity(D dto);
    
    D toDto(E entity);
    
}
