/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nebojsa.gymapp.service;

import com.nebojsa.gymapp.dto.MembershipPaymentDto;
import com.nebojsa.gymapp.model.MembershipPayment;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public interface MembershipPaymentService {
    
    MembershipPaymentDto save(MembershipPaymentDto param);
    
    void delete(Long id);
    
    List<MembershipPaymentDto>findAll();
    
    Optional<MembershipPaymentDto> findById(Long number);
    
    public MembershipPaymentDto findLatestMembershipPaymentForMember(Long id);
    
    public List<MembershipPaymentDto> findMembershipsByMemberId(Long memberId);
}
