/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.service.impl;

import com.nebojsa.gymapp.converter.impl.MembershipTypeConverter;
import com.nebojsa.gymapp.dto.MembershipTypeDto;
import com.nebojsa.gymapp.exception.InvalidEntityException;
import com.nebojsa.gymapp.model.MembershipType;
import com.nebojsa.gymapp.repository.MembershipTypeRepository;
import com.nebojsa.gymapp.service.MembershipTypeService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class MembershipTypeServiceImpl implements MembershipTypeService{

    private final MembershipTypeRepository mtRepository;
    private final MembershipTypeConverter mtConverter;

    public MembershipTypeServiceImpl(MembershipTypeRepository mtRepository, MembershipTypeConverter mtConverter) {
        this.mtRepository = mtRepository;
        this.mtConverter = mtConverter;
    }
    
    
    @Override
    public MembershipTypeDto save(MembershipTypeDto param) {
        MembershipType entity = mtConverter.toEntity(param);
        return mtConverter.toDto(mtRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        Optional<MembershipType> entity = mtRepository.findById(id);
        if(!entity.isPresent()){
            throw new InvalidEntityException("Membership type doesn't exist!");
        }
        mtRepository.deleteById(id);
    }

    @Override
    public List<MembershipTypeDto> findAll() {

        List<MembershipType> mTypes = mtRepository.findAll();
        
        return mTypes.stream().map(entity -> {
            return mtConverter.toDto(entity);
        }).collect(Collectors.toList());
        
    }

    @Override
    public Optional<MembershipTypeDto> findById(Long number) {
         Optional<MembershipType> entity = mtRepository.findById(number);
            System.out.println(entity);
        if (entity.isPresent()){
//            return coachConverter.toDto(entity);
            return entity.map(mtConverter::toDto);
        }else{
            throw new InvalidEntityException("Invalid MembershipType!");
            
        }
    }
    
}
