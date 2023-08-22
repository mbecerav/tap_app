/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.models;

import jakarta.persistence.*;
import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
public class ApplicantSubject implements Serializable {
    
    @EmbeddedId
    ApplicantSubjectKey id = new ApplicantSubjectKey();
    
    @ManyToOne
    @MapsId("applicantId")
    @JoinColumn(name = "applicant_id")
    Applicant applicant;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    Subject subject;

    int score;
    
}
