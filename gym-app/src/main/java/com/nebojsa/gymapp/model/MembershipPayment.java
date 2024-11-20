/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "membership_payment")
public class MembershipPayment implements AppEntity {
    
    @Id
    @GeneratedValue
    @Column(name = "membership_payment_id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;
    
    @ManyToOne
    @JoinColumn(name = "membership_type_id")
    private MembershipType membershipType;
    
    @Column(name = "payment_date")
    private Date paymentDate;
    
    @Column(name = "valid_until")
    private Date validUntil;
    
    @Column(name = "remaining_sessions")
    private int remainingSessions;
    
    @Column(name = "used_sessions")
    private int usedSessions;

    public MembershipPayment() {
    }

    public MembershipPayment(Long id, Member member, Coach coach, MembershipType membershipType, Date paymentDate, Date validUntil, int remainingSessions, int usedSessions) {
        this.id = id;
        this.member = member;
        this.coach = coach;
        this.membershipType = membershipType;
        this.paymentDate = paymentDate;
        this.validUntil = validUntil;
        this.remainingSessions = remainingSessions;
        this.usedSessions = usedSessions;
    }
    
    private Date calculateValidUntil(Date paymentDate) {

        LocalDate localPaymentDate = paymentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    
        LocalDate validUntilDate = localPaymentDate.plusMonths(1);
    
        return Date.from(validUntilDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
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
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.member);
        hash = 61 * hash + Objects.hashCode(this.coach);
        hash = 61 * hash + Objects.hashCode(this.membershipType);
        hash = 61 * hash + Objects.hashCode(this.paymentDate);
        hash = 61 * hash + Objects.hashCode(this.validUntil);
        hash = 61 * hash + this.remainingSessions;
        hash = 61 * hash + this.usedSessions;
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
        final MembershipPayment other = (MembershipPayment) obj;
        if (this.remainingSessions != other.remainingSessions) {
            return false;
        }
        if (this.usedSessions != other.usedSessions) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.member, other.member)) {
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
        return "MembershipPayment{" + "id=" + id + ", member=" + member.getFirstname()+" " + member.getLastname() + ", coach=" + coach.getFirstname()+" " + coach.getLastname() + ", membershipType=" + membershipType + ", paymentDate=" + paymentDate + ", validUntil=" + validUntil + ", remainingSessions=" + remainingSessions + ", usedSessions=" + usedSessions + '}';
    }

    
    
}

