/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class ViewTrainingSessionDto implements AppDto {
    private Long id;
    
    private Date date;
    
    private LocalTime time;
    
    private String coach;
//    private Long coach;
    
    private String member;
//    private Long member;
    
    private List<TrainingItemDto> trainingItems;

    public ViewTrainingSessionDto(Long id, Date date, LocalTime time, String coach, String member, List<TrainingItemDto> trainingItems) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.coach = coach;
        this.member = member;
        this.trainingItems = trainingItems;
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

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public List<TrainingItemDto> getTrainingItems() {
        return trainingItems;
    }

    public void setTrainingItems(List<TrainingItemDto> trainingItems) {
        this.trainingItems = trainingItems;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.date);
        hash = 23 * hash + Objects.hashCode(this.time);
        hash = 23 * hash + Objects.hashCode(this.coach);
        hash = 23 * hash + Objects.hashCode(this.member);
        hash = 23 * hash + Objects.hashCode(this.trainingItems);
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
        final ViewTrainingSessionDto other = (ViewTrainingSessionDto) obj;
        if (!Objects.equals(this.coach, other.coach)) {
            return false;
        }
        if (!Objects.equals(this.member, other.member)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return Objects.equals(this.trainingItems, other.trainingItems);
    }

    @Override
    public String toString() {
        return "ViewTrainingSessionDto{" + "id=" + id + ", date=" + date + ", time=" + time + ", coach=" + coach + ", member=" + member + ", trainingItems=" + trainingItems + '}';
    }
    
    
    
}
