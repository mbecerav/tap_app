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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class ApplicantSubjectKey implements Serializable {
    
    @Column(name = "applicant_id")
    int applicantId;
    
    @Column(name = "subject_id")
    int subjectId;
    
}
