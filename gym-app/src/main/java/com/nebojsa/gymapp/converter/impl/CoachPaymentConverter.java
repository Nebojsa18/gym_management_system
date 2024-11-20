/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.AppDto;
import com.nebojsa.gymapp.dto.CoachPaymentDto;
import com.nebojsa.gymapp.model.Coach;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class CoachPaymentConverter implements Converter<Coach, CoachPaymentDto>{

    @Override
    public Coach toEntity(CoachPaymentDto dto) {
        return new Coach(dto.getId());
    }

    @Override
    public CoachPaymentDto toDto(Coach entity) {
        return new CoachPaymentDto(entity.getId(), entity.getFirstname(), entity.getLastname());
    }
    
}
