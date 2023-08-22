/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.service;

import com.example.taptry.models.Applicant;
import com.example.taptry.models.ApplicantSubject;
import com.example.taptry.models.Subject;
import com.example.taptry.repository.ApplicantRepository;
import com.example.taptry.repository.ApplicantSubjectRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author becerraavilamauricio
 */
@Service
public class ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final SubjectService subjectService;
    private final ApplicantSubjectRepository applicantSubjectRepository;
    
    ApplicantService(ApplicantRepository applicantRepository, SubjectService subjectService, ApplicantSubjectRepository applicantSubjectRepository){
        this.applicantRepository = applicantRepository;
        this.subjectService = subjectService;
        this.applicantSubjectRepository = applicantSubjectRepository;
    }
    
    public List<Applicant> getAllApplicants(){
        return applicantRepository.findAll();
    }
    
    public Applicant createApplicant(Applicant applicant){
        return applicantRepository.save(applicant);
    }
    
    public Applicant getApplicantById(Integer id){
        return applicantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Applicant not found"));
    }
    
    public Applicant updateApplicant (Integer id, Applicant applicantDetail){
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Applicant not found"));
        
        applicant.setTotalScore(applicantDetail.getTotalScore());
        applicant.setCategoryId(applicantDetail.getCategoryId());
        applicant.setApplicantSubject(applicantDetail.getApplicantSubject());
        applicant.setCategoryTotal(applicantDetail.getCategoryTotal());
        
        
        return applicantRepository.save(applicant);
    }
    
    public void deleteApplicant(Integer id){
        applicantRepository.deleteById(id);
    }

    public void createApplicantWeb(HttpServletRequest request) {
        Map<String, String[]> formData = request.getParameterMap();
        Applicant applicant = applicantRepository.save(new Applicant());
        int totalScore = 0;
        int i = 0;
        int idAuto = applicant.getId();
        applicant = this.getApplicantById(idAuto);
        
        for (Map.Entry<String, String[]> entry : formData.entrySet()) {
            
            for(int j = 0; j < entry.getValue().length; j++){
                String fieldName = entry.getKey();
                String[] fieldValues = entry.getValue();
                if (fieldName.startsWith("score")) {
                    int score = Integer.parseInt(fieldValues[j]);
                    //System.out.println(entry.getKey() +": " + score);
                    totalScore = totalScore + score;
                    Subject subject = subjectService.getSubjectById(Character.getNumericValue(fieldName.charAt(fieldName.length() - 1)));
                    subject.setPoints(score);
                    ApplicantSubject applicantSubject = new ApplicantSubject();
                    applicantSubject.setApplicant(applicant);
                    applicantSubject.setSubject(subject);
                    applicantSubject.setScore(score);
                    applicantSubjectRepository.save(applicantSubject);
                }else if (fieldName.startsWith("category")) {
                    String category = fieldValues[j];
                    //System.out.println(entry.getKey() +": " + category);
                    applicant.setCategoryId(category);
                }
                
            }
            i++;
        }
        
        applicant.setTotalScore(totalScore);
        
        this.updateApplicant(idAuto, applicant);
        
    }

    public int getPassers() {
        List<Applicant> applicants = this.getAllApplicants();
        int passers = 0;
        for (Applicant applicant : applicants){
            if(applicant.isResult() == true)
                passers++;
        }
        System.out.println(passers);
        return passers;
    }

    public int getFails() {
        List<Applicant> applicants = this.getAllApplicants();
        int fails = 0;
        for (Applicant applicant : applicants){
            if(applicant.isResult() == false)
                fails++;
        }
        System.out.println(fails);
        return fails;
    }
    
}
