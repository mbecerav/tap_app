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
@Table(name = "subject")
@EqualsAndHashCode(callSuper = false)
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int points;
    private String name;
    private String categoryId;
    
    
    @OneToMany(mappedBy = "subject")
    private List<ApplicantSubject> applicantSubject;

    public Subject(int id, int points, String name, String categoryId) {
        this.id = id;
        this.points = points;
        this.name = name;
        this.categoryId = categoryId; 
    }
    
}
