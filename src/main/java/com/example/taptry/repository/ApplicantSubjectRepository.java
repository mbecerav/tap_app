/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.repository;

import com.example.taptry.models.ApplicantSubject;
import com.example.taptry.models.ApplicantSubjectKey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author becerraavilamauricio
 */
@Repository
public interface ApplicantSubjectRepository extends JpaRepository<ApplicantSubject, ApplicantSubjectKey> {
    
    //AQUI DEBEN IR SIEMPRE CLASES Y NO TABLAS
    @Transactional
    @Query("SELECT SUM(asb.score) FROM ApplicantSubject asb, Subject s, Applicant a WHERE a.categoryId = :categoryId AND asb.applicant.id = :applicantId AND a.categoryId = s.categoryId AND s.id = asb.subject.id")
    public int getCategoryTotal(@Param("categoryId") String categoryId, @Param("applicantId") int applicantId);

    //AQUI DEBEN IR SIEMPRE CLASES Y NO TABLAS
    @Transactional
    @Modifying
    @Query("DELETE FROM ApplicantSubject asb WHERE asb.subject.id = :id")
    public void deleteAllApplicantsSubjectRelationById(@Param("id") Integer id);
    
}
