/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry;

import com.example.taptry.models.Category;
import com.example.taptry.models.Subject;
import com.example.taptry.service.CategoryService;
import com.example.taptry.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author becerraavilamauricio
 */

@Configuration
public class InitConfiguration {
    public static final int totalMinimumScore = 350;
    private final SubjectService subjectService;
    private final CategoryService categoryService;
    
    @Autowired
    public InitConfiguration(SubjectService subjectService, CategoryService categoryService){
        this.subjectService = subjectService;
        this.categoryService = categoryService;
    }
    
    @Bean
    public CategoryService categoryServ(){
        categoryService.createCategory(new Category("","",0));
        categoryService.createCategory(new Category("s","Science",160));
        categoryService.createCategory(new Category("l","Humanities",160));
        return categoryService;
    }
    
    @Bean
    public SubjectService subjectServ() {     
        subjectService.createSubject(new Subject(1,0,"English", ""));
        subjectService.createSubject(new Subject(2,0,"Science", "s"));
        subjectService.createSubject(new Subject(3,0,"Mathematics", "s"));
        subjectService.createSubject(new Subject(4,0,"Japanese", "l"));
        subjectService.createSubject(new Subject(5,0,"GeoHis", "l"));
        return subjectService; 
    }
    
}
