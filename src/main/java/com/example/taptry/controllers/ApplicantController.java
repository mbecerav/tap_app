/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.controllers;

import com.example.taptry.models.Applicant;
import com.example.taptry.service.ApplicantService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/api/applicant")
public class ApplicantController {
    private final ApplicantService applicantService;
    
    @Autowired
    public ApplicantController(ApplicantService applicantService){
        this.applicantService = applicantService;
    }
    
       @GetMapping
    public ResponseEntity<List<Applicant>> getApplicants(){
        List<Applicant> applicants = applicantService.getAllApplicants();
        return ResponseEntity.ok(applicants);
    }
    
    @PostMapping
    public ResponseEntity<Applicant> createApplicant(@RequestBody Applicant applicant){
        Applicant createdApplicant = applicantService.createApplicant(applicant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApplicant);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getApplicantById(@PathVariable Integer id){
        Applicant applicant = applicantService.getApplicantById(id);
        return ResponseEntity.ok(applicant);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable Integer id, @RequestBody Applicant applicantDetail){
        Applicant updatedApplicant = applicantService.updateApplicant(id, applicantDetail);
        return ResponseEntity.ok(updatedApplicant);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Integer id){
        applicantService.deleteApplicant(id);
        return ResponseEntity.noContent().build();
    }
    
}
