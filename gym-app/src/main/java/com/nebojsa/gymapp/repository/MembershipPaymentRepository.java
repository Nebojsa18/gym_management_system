/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nebojsa.gymapp.repository;

import com.nebojsa.gymapp.model.MembershipPayment;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Admin
 */
public interface MembershipPaymentRepository extends JpaRepository<MembershipPayment, Long>{

    @Query("SELECT mp FROM MembershipPayment mp WHERE mp.member.id = :memberId ORDER BY mp.paymentDate DESC")
    List<MembershipPayment> findLatestMembershipPaymentForMember(@Param("memberId") Long memberId);
    
    @Query("SELECT mp FROM MembershipPayment mp WHERE mp.member.id = :memberId ORDER BY mp.paymentDate DESC")
    List<MembershipPayment> findMembershipsByMemberId(Long memberId);
}

    

