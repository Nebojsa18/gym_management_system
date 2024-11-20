/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nebojsa.gymapp.repository;

import com.nebojsa.gymapp.model.Coach;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Admin
 */
public interface CoachRepository extends JpaRepository<Coach, Long>{
    
//    @Query("Select c.coach_id, c.firstname, c.lastname, c.birthday, c.gender, c.phone, c.username, c.password FROM coach AS c WHERE c.username=?1")
//    Optional<Coach> findByUsername(String username);
    
//    Optional<Coach> findByUsername(String username);
    
    @Query("SELECT c FROM Coach c WHERE c.username = ?1")
    Optional<Coach> findByUsername(String username);
    
// 
    
}
