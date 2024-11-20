/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.service.impl;

import com.nebojsa.gymapp.converter.impl.MemberConverter;
import com.nebojsa.gymapp.dto.MemberDto;
import com.nebojsa.gymapp.exception.InvalidEntityException;
import com.nebojsa.gymapp.model.Member;
import com.nebojsa.gymapp.repository.MemberRepository;
import com.nebojsa.gymapp.service.MemberService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */

@Service
public class MemberServiceImpl implements MemberService{
    
    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    public MemberServiceImpl(MemberRepository memberRepository, MemberConverter memberConverter) {
        this.memberRepository = memberRepository;
        this.memberConverter = memberConverter;
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Override
    public MemberDto save(MemberDto param) {
        System.out.println(param);
        
        Member memberEntity = memberConverter.toEntity(param);
        Optional<Member> entity = memberRepository.findByEmail(memberEntity.getEmail());
            System.out.println(memberEntity);
        if (entity.isPresent() && !entity.get().getId().equals(memberEntity.getId())){
                throw new InvalidEntityException("Member with email: " + memberEntity.getEmail() + " already exists!");
            }
        return memberConverter.toDto(memberRepository.save(memberEntity));
    }

    @Override
    public void delete(Long id) {
        Optional<Member> entity = memberRepository.findById(id);
        if(!entity.isPresent()){
            throw new InvalidEntityException("Member doesn't exist!");
        }
        memberRepository.deleteById(id);
    }

    @Override
    public List<MemberDto> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(entity ->{
        return memberConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<MemberDto> findById(Long number) {
        Optional<Member> entity = memberRepository.findById(number);
            System.out.println(entity);
        if (entity.isPresent()){
//            return memberConverter.toDto(entity);
            return entity.map(memberConverter::toDto);
        }else{
            System.out.println("ovde sam");
            throw new InvalidEntityException("Invalid member!");
            
        }
    }
    
    
    
}
