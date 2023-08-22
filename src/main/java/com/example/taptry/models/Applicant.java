/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.models;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
/**
 *
 * @author becerraavilamauricio
 */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "applicant")
@EqualsAndHashCode(callSuper = false)
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int totalScore;
    private String categoryId;
    
    @OneToMany(mappedBy = "applicant")
    private List<ApplicantSubject> applicantSubject;
    
    private int categoryTotal;
    private boolean result;
    
    
}
