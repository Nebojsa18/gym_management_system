/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 *
 * @author Admin
 */

@Entity
@Table(name = "trainingitem")
public class TrainingItem implements AppEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="training_item_id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="training_session_id")
    private TrainingSession trainingSession;
    
    @ManyToOne
    @JoinColumn(name="exercise_id")
    private Exercise exercise;
    
    @Column(name="num_of_reps")
    private int numOfReps;

    public TrainingItem() {
    }

    public TrainingItem(Long id, TrainingSession trainingSession, Exercise exercise, int numOfReps) {
        this.id = id;
        this.trainingSession = trainingSession;
        this.exercise = exercise;
        this.numOfReps = numOfReps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getNumOfReps() {
        return numOfReps;
    }

    public void setNumOfReps(int numOfReps) {
        this.numOfReps = numOfReps;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.trainingSession);
        hash = 97 * hash + Objects.hashCode(this.exercise);
        hash = 97 * hash + this.numOfReps;
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
        final TrainingItem other = (TrainingItem) obj;
        if (this.numOfReps != other.numOfReps) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.trainingSession, other.trainingSession)) {
            return false;
        }
        return Objects.equals(this.exercise, other.exercise);
    }

    @Override
    public String toString() {
        return "TrainingItem{" + "id=" + id + ", trainingSessionid=" + trainingSession.getId() + ", exercise=" + exercise + ", numOfReps=" + numOfReps + '}';
    }
            
    
    
    
    
    
}
