/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.controllers;

import com.example.taptry.models.Subject;
import com.example.taptry.service.SubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author becerraavilamauricio
 */

@Controller
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;
    
    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }
    
    @GetMapping
    //public ResponseEntity<List<Subject>> getAllSubjects(){
    public String getAllSubjects(Model model){
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        //return ResponseEntity.ok(subjects);
        return("subjects");
    }
    
    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        Subject createdSubject = subjectService.createSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id){
        Subject subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }
    
    @GetMapping("/c/{id}")
    public ResponseEntity<List<Subject>> getAllSubjectsByCatId(@PathVariable String id){
        List<Subject> subjects = subjectService.getAllSubjectByCategory(id);
        return ResponseEntity.ok(subjects);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Integer id, @RequestBody Subject subject){
        Subject updatedSubject = subjectService.updateSubject(id, subject);
        return ResponseEntity.ok(updatedSubject);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Integer id){
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
