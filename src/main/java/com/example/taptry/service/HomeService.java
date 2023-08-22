/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.service;

import com.example.taptry.InitConfiguration;
import com.example.taptry.models.Applicant;
import com.example.taptry.models.Category;
import com.example.taptry.repository.ApplicantSubjectRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author becerraavilamauricio
 */
@Service
public class HomeService {   
    private static int totalMinimumScore = InitConfiguration.totalMinimumScore;
    private final ApplicantSubjectRepository appSubRep;
    private final CategoryService categoryService;
    private final ApplicantService applicantService;
    

    public HomeService(ApplicantSubjectRepository appSubRep, CategoryService categoryService, ApplicantService applicantService) {
        this.appSubRep = appSubRep;
        this.categoryService = categoryService;
        this.applicantService = applicantService;
    }
    
    public void updateEvaluationWeb() {
        List<Applicant> applicants = applicantService.getAllApplicants();
        int categoryMinimumScore = 0;
                
        for(Applicant applicant : applicants){
            try{
                categoryMinimumScore = categoryService.getCategoryMinimumScoreById(applicant.getCategoryId());
                applicant.setResult(false);
                applicantService.updateApplicant(applicant.getId(), applicant);
            }
            catch(IllegalArgumentException e){
                 continue;
            }
            int applicantTotalScore = applicant.getTotalScore();
            int applicantCateogryScore = appSubRep.getCategoryTotal(applicant.getCategoryId(), applicant.getId());
            if ( applicantCateogryScore >= categoryMinimumScore && applicantTotalScore >= totalMinimumScore){
                System.out.println("Aprobo por puntos minimos en categoria");
                applicant.setResult(true);
                applicantService.updateApplicant(applicant.getId(), applicant);
            }
            else{
                System.out.println("El Applicante necesitaba " + categoryMinimumScore + "ptos para aprobar en " + applicant.getCategoryId() + ", pero obtuvo " + applicantCateogryScore + "ptos.");
            }
        }

    }
    
}
