/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.service.impl;

import com.nebojsa.gymapp.converter.impl.MembershipPaymentConverter;
import com.nebojsa.gymapp.dto.MembershipPaymentDto;
import com.nebojsa.gymapp.exception.InvalidEntityException;
import com.nebojsa.gymapp.model.MembershipPayment;
import com.nebojsa.gymapp.repository.MembershipPaymentRepository;
import com.nebojsa.gymapp.service.MembershipPaymentService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class MembershipPaymentServiceImpl implements MembershipPaymentService{

    private final MembershipPaymentRepository mpRepository;
    private final MembershipPaymentConverter mpConverter;

    public MembershipPaymentServiceImpl(MembershipPaymentRepository mpRepository, MembershipPaymentConverter mpConverter) {
        this.mpRepository = mpRepository;
        this.mpConverter = mpConverter;
    }
    
    @Override
    public MembershipPaymentDto save(MembershipPaymentDto param) {

        System.out.println("Dto membershippayment za cuvanje " + param);
        
        List<MembershipPayment> latestPayments =  mpRepository.findLatestMembershipPaymentForMember(param.getMember().getId());
        
        if (!latestPayments.isEmpty()) {
            MembershipPayment latestPayment = latestPayments.get(0);
             if (latestPayment.getId().equals(param.getId())) {
                // Ako je trenutni unos ažuriranje postojećeg zapisa, samo ažuriraj
                MembershipPayment entity = mpConverter.toEntity(param);
                return mpConverter.toDto(mpRepository.save(entity));
             }else{
                 checkMembershipValidity(param);
                MembershipPayment entity = mpConverter.toEntity(param);
                return mpConverter.toDto(mpRepository.save(entity));
             }
        }else{
            checkMembershipValidity(param);
            MembershipPayment entity = mpConverter.toEntity(param);
            return mpConverter.toDto(mpRepository.save(entity));
        }
//            else(latestPayment.getValidUntil().before(param.getPaymentDate())){
//                MembershipPayment entity = mpConverter.toEntity(param);
//
//                System.out.println("Entity membershippayment za cuvanje " + entity);
//
//                return mpConverter.toDto(mpRepository.save(entity));
//            }else{
//                throw new InvalidEntityException("Member has a valid membership");
//            }
//        }else {
//            MembershipPayment entity = mpConverter.toEntity(param);
//
//            System.out.println("Entity membershippayment za cuvanje " + entity);
//
//            return mpConverter.toDto(mpRepository.save(entity));
//        }
        
    }
    
    private void checkMembershipValidity(MembershipPaymentDto param) {
        MembershipPayment latestPayment = mpRepository.findLatestMembershipPaymentForMember(param.getMember().getId()).stream().findFirst().orElse(null);
        if (latestPayment != null && latestPayment.getValidUntil().after(param.getPaymentDate())) {
            throw new InvalidEntityException("Member has a valid membership");
        }   
    }

    @Override
    public void delete(Long id) {

        Optional<MembershipPayment> entity = mpRepository.findById(id);
        if(!entity.isPresent()){
            throw new InvalidEntityException("Membership payment doesn't exist!");
        }
        mpRepository.deleteById(id);
        
    }

    @Override
    public List<MembershipPaymentDto> findAll() {

        List<MembershipPayment> mpList = mpRepository.findAll();
        
        return mpList.stream().map(entity -> {
            return mpConverter.toDto(entity);
        }).collect(Collectors.toList());
        
    }

    @Override
    public Optional<MembershipPaymentDto> findById(Long number) {

        Optional<MembershipPayment> entity = mpRepository.findById(number);
            System.out.println(entity);
        if (entity.isPresent()){
//            return coachConverter.toDto(entity);
            return entity.map(mpConverter::toDto);
        }else{
            throw new InvalidEntityException("Invalid MembershipPayment!");
            
        }
        
    }
    
    @Override
    public MembershipPaymentDto findLatestMembershipPaymentForMember(Long memberId) {
        List<MembershipPayment> membershipPayments = mpRepository.findLatestMembershipPaymentForMember(memberId);
        return membershipPayments.isEmpty() ? null : mpConverter.toDto(membershipPayments.get(0));
    }

    @Override
    public List<MembershipPaymentDto> findMembershipsByMemberId(Long memberId) {

        List<MembershipPayment> membershipPayments = mpRepository.findMembershipsByMemberId(memberId);
        
        return membershipPayments.stream().map(entity -> {
            return mpConverter.toDto(entity);
        }).collect(Collectors.toList());
        
    }
    
}
