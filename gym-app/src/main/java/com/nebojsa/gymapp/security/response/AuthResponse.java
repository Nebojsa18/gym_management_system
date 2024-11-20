/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.security.response;

/**
 *
 * @author Admin
 */



public class AuthResponse { 
	private String jwt; 
	private String message; 
	private Boolean status; 

    public AuthResponse() {
    }

    public AuthResponse(String jwt, String message, Boolean status) {
        this.jwt = jwt;
        this.message = message;
        this.status = status;
    }

    public String getJwt() { 
        return jwt; 
    } 

    public void setJwt(String jwt) { 
	this.jwt = jwt; 
    } 

    public String getMessage() { 
	return message; 
    } 

    public void setMessage(String message) { 
    	this.message = message; 
    } 
    
    public Boolean getStatus() { 
        return status; 
    } 
    
    public void setStatus(Boolean status) { 
        this.status = status; 
    }    
        
	 
} 
