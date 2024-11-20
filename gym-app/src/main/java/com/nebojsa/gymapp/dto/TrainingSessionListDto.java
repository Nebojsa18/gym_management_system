/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class TrainingSessionListDto implements AppDto{
    
    private Long id;
    
    private Date date;
    
    private LocalTime time;
    
    private String coachName;
//    private Long coach;
    
    private String memberName;

    public TrainingSessionListDto(Long id, Date date, LocalTime time, String coachName, String memberName) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.coachName = coachName;
        this.memberName = memberName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.date);
        hash = 43 * hash + Objects.hashCode(this.time);
        hash = 43 * hash + Objects.hashCode(this.coachName);
        hash = 43 * hash + Objects.hashCode(this.memberName);
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
        final TrainingSessionListDto other = (TrainingSessionListDto) obj;
        if (!Objects.equals(this.coachName, other.coachName)) {
            return false;
        }
        if (!Objects.equals(this.memberName, other.memberName)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return Objects.equals(this.time, other.time);
    }

    @Override
    public String toString() {
        return "TrainingSessionListDto{" + "id=" + id + ", date=" + date + ", time=" + time + ", coachName=" + coachName + ", memberName=" + memberName + '}';
    }
    
    
    
}
