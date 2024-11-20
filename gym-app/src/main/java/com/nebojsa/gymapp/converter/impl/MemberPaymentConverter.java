/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.AppDto;
import com.nebojsa.gymapp.dto.MemberPaymentDto;
import com.nebojsa.gymapp.model.Gender;
import com.nebojsa.gymapp.model.Member;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class MemberPaymentConverter implements Converter<Member, MemberPaymentDto> {

    @Override
    public Member toEntity(MemberPaymentDto dto) {
        return new Member(dto.getId(),dto.getFirstname() , dto.getLastname(), null, "", Gender.MALE, "");
    }

    @Override
    public MemberPaymentDto toDto(Member entity) {
        return new MemberPaymentDto(entity.getId(), entity.getFirstname(), entity.getLastname());
    }
    
}
