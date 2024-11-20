/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.AppDto;
import com.nebojsa.gymapp.dto.MembershipTypeDto;
import com.nebojsa.gymapp.model.MembershipType;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class MembershipTypeConverter implements Converter<MembershipType, MembershipTypeDto>{

    @Override
    public MembershipType toEntity(MembershipTypeDto dto) {
        return new MembershipType(dto.getId(), dto.getName(), dto.getNumOfSessions(), dto.getPrice(), dto.getDescription());
    }

    @Override
    public MembershipTypeDto toDto(MembershipType entity) {
        return new MembershipTypeDto(entity.getId(), entity.getName(), entity.getNumOfSessions(), entity.getPrice(), entity.getDescription());
    }
    
}
