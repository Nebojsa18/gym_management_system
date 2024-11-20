/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nebojsa.gymapp.dto;

import com.nebojsa.gymapp.enums.Role;
import com.nebojsa.gymapp.model.Gender;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class CoachDto implements AppDto{
    
    private Long id;
    private String firstname;
    private String lastname;
    
    private Date birthday;
    private Role role;
    private Gender gender;
    private String phone;
    private String username;
    private String password;
    private String token;

    public CoachDto() {
    }

    public CoachDto(String firstname, String lastname, Date birthday, Role role, Gender gender, String phone, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.role = role;
        this.gender = gender;
        this.phone = phone;
        this.username = username;
        this.password = password;
        
    }

    public CoachDto(Long id, String firstname, String lastname, Date birthday, Role role, Gender gender, String phone, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.role = role;
        this.gender = gender;
        this.phone = phone;
        this.username = username;
        this.password = password;
        
    }

    
    

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    

    @Override
    public String toString() {
        return "CoachDto{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", birthday=" + birthday + ", gender=" + gender + ", phone=" + phone + ", username=" + username + ", password=" + password + '}';
    }
    
    
    
}
