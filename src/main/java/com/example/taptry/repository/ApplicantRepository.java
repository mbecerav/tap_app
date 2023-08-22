/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.repository;

import com.example.taptry.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author becerraavilamauricio
 */
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    
}
