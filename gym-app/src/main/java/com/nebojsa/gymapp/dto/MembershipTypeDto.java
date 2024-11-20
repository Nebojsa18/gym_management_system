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
public class MembershipTypeDto implements AppDto{
    
    private Long id;
    
    private String name;
    
    private int numOfSessions;

    private double price;
    
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSessions() {
        return numOfSessions;
    }

    public void setNumOfSessions(int numOfSessions) {
        this.numOfSessions = numOfSessions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MembershipTypeDto() {
    }

    public MembershipTypeDto(Long id, String name, int numOfSessions, double price, String description) {
        this.id = id;
        this.name = name;
        this.numOfSessions = numOfSessions;
        this.price = price;
        this.description = description;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + this.numOfSessions;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 19 * hash + Objects.hashCode(this.description);
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
        final MembershipTypeDto other = (MembershipTypeDto) obj;
        if (this.numOfSessions != other.numOfSessions) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "MembershipTypeDto{" + "id=" + id + ", name=" + name + ", numOfSessions=" + numOfSessions + ", price=" + price + ", description=" + description + '}';
    }

    
    
}
