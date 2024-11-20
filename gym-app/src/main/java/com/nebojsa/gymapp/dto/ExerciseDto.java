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
public class ExerciseDto implements AppDto{
    
    private Long id;
    private String exerciseName;

    public ExerciseDto() {
    }

    public ExerciseDto(Long id, String exerciseName) {
        this.id = id;
        this.exerciseName = exerciseName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.exerciseName);
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
        final ExerciseDto other = (ExerciseDto) obj;
        if (!Objects.equals(this.exerciseName, other.exerciseName)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ExerciseDto{" + "id=" + id + ", exerciseName=" + exerciseName + '}';
    }

    
    
    
    
    
}
