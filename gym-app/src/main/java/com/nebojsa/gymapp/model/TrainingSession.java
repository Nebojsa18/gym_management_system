/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="trainingsession")
public class TrainingSession implements AppEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="training_session_id")
    private Long id;
    
    @Column(name="date", columnDefinition = "DATE")
    private Date date;
    
    @Column(name="time", columnDefinition="TIME")
    private LocalTime time;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coach_id")
    private Coach coach;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;
    
    @OneToMany(mappedBy="trainingSession", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TrainingItem> trainingItems;

    public TrainingSession() {
    }

    public TrainingSession(Long id, Date date, LocalTime time, Coach coach, Member member, List<TrainingItem> trainingItems) {
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

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<TrainingItem> getTrainingItems() {
        return trainingItems;
    }

    public void setTrainingItems(List<TrainingItem> trainingItems) {
        this.trainingItems = trainingItems;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.date);
        hash = 13 * hash + Objects.hashCode(this.time);
        hash = 13 * hash + Objects.hashCode(this.coach);
        hash = 13 * hash + Objects.hashCode(this.member);
        hash = 13 * hash + Objects.hashCode(this.trainingItems);
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
        final TrainingSession other = (TrainingSession) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.coach, other.coach)) {
            return false;
        }
        if (!Objects.equals(this.member, other.member)) {
            return false;
        }
        return Objects.equals(this.trainingItems, other.trainingItems);
    }

    @Override
    public String toString() {
        return "TrainingSession{" + "id=" + id + ", date=" + date + ", time=" + time + ", coach=" + coach + ", member=" + member + ", trainingItems=" + trainingItems + '}';
    }
    
    
    
    
    
    
}
