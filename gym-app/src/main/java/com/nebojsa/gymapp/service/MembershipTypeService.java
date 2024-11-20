/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nebojsa.gymapp.service;

import com.nebojsa.gymapp.dto.MembershipTypeDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public interface MembershipTypeService {
    MembershipTypeDto save(MembershipTypeDto param);
    
    void delete(Long id);
    
    List<MembershipTypeDto>findAll();
    
    Optional<MembershipTypeDto> findById(Long number);
}
