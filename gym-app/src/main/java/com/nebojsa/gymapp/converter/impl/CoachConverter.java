/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.CoachDto;
import com.nebojsa.gymapp.enums.Role;
import com.nebojsa.gymapp.model.Coach;
import com.nebojsa.gymapp.model.Gender;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class CoachConverter implements Converter<Coach, CoachDto>{

    @Override
    public Coach toEntity(CoachDto dto) {
        return new Coach(dto.getId(),dto.getFirstname(),dto.getLastname(),dto.getBirthday(),dto.getGender(),dto.getRole(),dto.getPhone(),dto.getUsername(),dto.getPassword());
    }

    @Override
    public CoachDto toDto(Coach entity) {
//        return new CoachDto(entity.getId(),entity.getFirstname(),entity.getLastname(),entity.getBirthday(),entity.getRole(),entity.getGender(),entity.getPhone(),entity.getUsername(),entity.getPassword());
        return new CoachDto(entity.getId(), entity.getFirstname(), entity.getLastname(), entity.getBirthday(), entity.getRole(), entity.getGender(), entity.getPhone(), entity.getUsername(), entity.getPassword());
    }
    
    
    
}
