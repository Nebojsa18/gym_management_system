/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.dto;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class MembershipPaymentDto implements AppDto{
    
    private Long id;
    
    private MemberPaymentDto member;
    
    private CoachPaymentDto coach;
    
    private MembershipTypeDto membershipType;
    
    private Date paymentDate;
    
    private Date validUntil;
    
    private int remainingSessions;
    
    private int usedSessions;

    public MembershipPaymentDto(Long id, MemberPaymentDto member, CoachPaymentDto coach, MembershipTypeDto membershipType, Date paymentDate, Date validUntil, int remainingSessions, int usedSessions) {
        this.id = id;
        this.member = member;
        this.coach = coach;
        this.membershipType = membershipType;
        this.paymentDate = paymentDate;
        this.validUntil = validUntil;
        this.remainingSessions = remainingSessions;
        this.usedSessions = usedSessions;
    }

    public MembershipPaymentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberPaymentDto getMember() {
        return member;
    }

    public void setMember(MemberPaymentDto member) {
        this.member = member;
    }

    public CoachPaymentDto getCoach() {
        return coach;
    }

    public void setCoach(CoachPaymentDto coach) {
        this.coach = coach;
    }

    public MembershipTypeDto getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipTypeDto membershipType) {
        this.membershipType = membershipType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public int getRemainingSessions() {
        return remainingSessions;
    }

    public void setRemainingSessions(int remainingSessions) {
        this.remainingSessions = remainingSessions;
    }

    public int getUsedSessions() {
        return usedSessions;
    }

    public void setUsedSessions(int usedSessions) {
        this.usedSessions = usedSessions;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.member);
        hash = 41 * hash + Objects.hashCode(this.coach);
        hash = 41 * hash + Objects.hashCode(this.membershipType);
        hash = 41 * hash + Objects.hashCode(this.paymentDate);
        hash = 41 * hash + Objects.hashCode(this.validUntil);
        hash = 41 * hash + this.remainingSessions;
        hash = 41 * hash + this.usedSessions;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MembershipPaymentDto other = (MembershipPaymentDto) obj;
        if (this.remainingSessions != other.remainingSessions) {
            return false;
        }
        if (this.usedSessions != other.usedSessions) {
            return false;
        }
        if (!Objects.equals(this.member, other.member)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.coach, other.coach)) {
            return false;
        }
        if (!Objects.equals(this.membershipType, other.membershipType)) {
            return false;
        }
        if (!Objects.equals(this.paymentDate, other.paymentDate)) {
            return false;
        }
        return Objects.equals(this.validUntil, other.validUntil);
    }

    @Override
    public String toString() {
        return "MembershipPaymentDto{" + "id=" + id + ", member=" + member + ", coach=" + coach + ", membershipType=" + membershipType + ", paymentDate=" + paymentDate + ", validUntil=" + validUntil + ", remainingSessions=" + remainingSessions + ", usedSessions=" + usedSessions + '}';
    }
    
    
    
}
