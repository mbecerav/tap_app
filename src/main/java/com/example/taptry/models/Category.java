/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.models;

import jakarta.persistence.*;
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
@Table(name = "category")
@EqualsAndHashCode(callSuper = false)
public class Category {
    
    @Id
    private String id;
    
    private String name;
    private int minTotalPoints;

    
}
