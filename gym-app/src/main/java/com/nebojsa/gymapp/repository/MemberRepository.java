/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nebojsa.gymapp.repository;

import com.nebojsa.gymapp.model.Coach;
import com.nebojsa.gymapp.model.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Admin
 */
public interface MemberRepository extends JpaRepository<Member, Long>{
    
    @Query("SELECT m FROM Member m WHERE m.email = ?1")
    Optional<Member> findByEmail(String email);
    
}
