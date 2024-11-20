/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.CoachDto;
import com.nebojsa.gymapp.dto.MemberDto;
import com.nebojsa.gymapp.model.Member;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class MemberConverter implements Converter<Member, MemberDto> {

    @Override
    public Member toEntity(MemberDto dto) {
        return new Member(dto.getId(),dto.getFirstname(),dto.getLastname(),dto.getBirthday(),dto.getEmail(),dto.getGender(),dto.getPhone());
    }

    @Override
    public MemberDto toDto(Member entity) {
         return new MemberDto(entity.getId(),entity.getFirstname(),entity.getLastname(),entity.getBirthday(),entity.getEmail(),entity.getGender(),entity.getPhone());
    }
    
}
