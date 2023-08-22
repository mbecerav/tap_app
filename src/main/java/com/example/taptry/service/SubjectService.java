/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.service;

import com.example.taptry.models.Subject;
import com.example.taptry.repository.ApplicantSubjectRepository;
import com.example.taptry.repository.SubjectRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author becerraavilamauricio
 */
@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final ApplicantSubjectRepository applicantSubjectRepository;
    
    SubjectService(SubjectRepository subjectRepository, ApplicantSubjectRepository applicantSubjectRepository){
        this.subjectRepository = subjectRepository;
        this.applicantSubjectRepository = applicantSubjectRepository;
    }
    
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }
    
    public Subject createSubject(Subject subject){
        return subjectRepository.save(subject);
    }
    
    public Subject getSubjectById(Integer id){
        return subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));
    }
    
    public List<Subject> getAllSubjectByCategory(String categoryId){
        return subjectRepository.getSubjectByCategoryId(categoryId);
    }
    
    public Subject updateSubject (Integer id, Subject subjectDetail){
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));
        
        subject.setName(subjectDetail.getName());
        subject.setCategoryId(subjectDetail.getCategoryId());
        
        return subjectRepository.save(subject);
    }
    
    @Transactional
    public void deleteSubject(Integer id){
        applicantSubjectRepository.deleteAllApplicantsSubjectRelationById(id);
        subjectRepository.deleteById(id);
    }

    public void updateSubjectWeb(String id) {//Format e,g, 5s   Subject ID 5 => NewCategory S
        String strSubId = id.charAt(0)+"";
        int subId = Integer.parseInt(strSubId);
        String subjectCategory = id.charAt(1)+"";
        Subject subject = this.getSubjectById(subId);
        subject.setCategoryId(subjectCategory);
        this.updateSubject(subId, subject);
    }
    
    
}
