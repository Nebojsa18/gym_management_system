/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.dto;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class TrainingItemDto implements AppDto{
    
    private Long id;
    
//    private TrainingSessionDto trainingSession;
    
    private ExerciseDto exercise;
    
    private int numOfReps;

    public TrainingItemDto() {
    }

    public TrainingItemDto(Long id, ExerciseDto exercise, int numOfReps) {
        this.id = id;
        this.exercise = exercise;
        this.numOfReps = numOfReps;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public TrainingSessionDto getTrainingSession() {
//        return trainingSession;
//    }
//
//    public void setTrainingSession(TrainingSessionDto trainingSession) {
//        this.trainingSession = trainingSession;
//    }

    public ExerciseDto getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseDto exercise) {
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
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.exercise);
        hash = 23 * hash + this.numOfReps;
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
        final TrainingItemDto other = (TrainingItemDto) obj;
        if (this.numOfReps != other.numOfReps) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.exercise, other.exercise);
    }

    @Override
    public String toString() {
        return "TrainingItemDto{" + "id=" + id + ", exercise=" + exercise + ", numOfReps=" + numOfReps + '}';
    }

    

    

    
    
    
}
