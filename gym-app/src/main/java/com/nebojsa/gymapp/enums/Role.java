/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.nebojsa.gymapp.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Admin
 */
public enum Role implements GrantedAuthority{
    OWNER,EMPLOYEE;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
