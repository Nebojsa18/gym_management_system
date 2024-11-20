/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.converter.impl;

import com.nebojsa.gymapp.converter.Converter;
import com.nebojsa.gymapp.dto.AppDto;
import com.nebojsa.gymapp.dto.MembershipPaymentDto;
import com.nebojsa.gymapp.model.Coach;
import com.nebojsa.gymapp.model.MembershipPayment;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class MembershipPaymentConverter implements Converter<MembershipPayment, MembershipPaymentDto>{
    
    private final MembershipTypeConverter mtConverter;
    private final MemberPaymentConverter mpConverter;
    private final CoachPaymentConverter cpConverter;

    public MembershipPaymentConverter(MembershipTypeConverter mtConverter, MemberPaymentConverter mpConverter, CoachPaymentConverter cpConverter) {
        this.mtConverter = mtConverter;
        this.mpConverter = mpConverter;
        this.cpConverter = cpConverter;
    }
    
    

    @Override
    public MembershipPayment toEntity(MembershipPaymentDto dto) {
        return new MembershipPayment(dto.getId(), mpConverter.toEntity(dto.getMember()), cpConverter.toEntity(dto.getCoach()), mtConverter.toEntity(dto.getMembershipType()), dto.getPaymentDate(), dto.getValidUntil(), dto.getRemainingSessions(), dto.getUsedSessions());
    }

    @Override
    public MembershipPaymentDto toDto(MembershipPayment entity) {
        return new MembershipPaymentDto(entity.getId(), mpConverter.toDto(entity.getMember()),
                cpConverter.toDto(entity.getCoach()), mtConverter.toDto(entity.getMembershipType()),
                entity.getPaymentDate(), entity.getValidUntil(), entity.getRemainingSessions(), entity.getUsedSessions());
    }
    
    
}
