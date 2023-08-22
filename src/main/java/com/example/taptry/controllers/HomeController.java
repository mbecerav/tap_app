/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.controllers;

import com.example.taptry.models.Category;
import com.example.taptry.models.Subject;
import com.example.taptry.service.ApplicantService;
import com.example.taptry.service.CategoryService;
import com.example.taptry.service.HomeService;
import com.example.taptry.service.SubjectService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author becerraavilamauricio
 */

@Controller
@RequestMapping("/")
public class HomeController {
    private final SubjectService subjectService;
    private final CategoryService categoryService;
    private final ApplicantService applicantService;
    private final HomeService homeService;

    @Autowired
    public HomeController(SubjectService subjectService, ApplicantService applicantService, CategoryService categoryService, HomeService homeService) {
        this.subjectService = subjectService;
        this.categoryService = categoryService;
        this.applicantService = applicantService;
        this.homeService = homeService;
    }
    
    @GetMapping
    public String index(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Category> categories = categoryService.getAllCategories();
        List<String> availableCategories = categoryService.getAvailableCategories();
        model.addAttribute("subjects", subjects);
        model.addAttribute("categories", categories);
        model.addAttribute("availableCategories", availableCategories);
        return "Index";
    }
    
    @PostMapping("addApp")
    @ResponseStatus(value = HttpStatus.OK)
    public void createApplicant(HttpServletRequest request) {
        applicantService.createApplicantWeb(request);
    }
    
    @PostMapping("updateEvaluation")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Integer>> updateEvaluation(Model model){
        homeService.updateEvaluationWeb();
        List<Integer> response = new ArrayList();
        response.add(applicantService.getPassers());
        response.add(applicantService.getFails());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("deleteCategory/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return "redirect:/index";
    }
    
    @PostMapping("addCategory")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> createCategory(
            @RequestParam("category-id") String categoryId,
            @RequestParam("category-name") String categoryName,
            @RequestParam("category-score") String categoryScore) {
        categoryService.createCategoryWeb(categoryId, categoryName, categoryScore);
        return ResponseEntity.ok("Processed");
    }
    
    @PostMapping("deleteSubject/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteSubject(@PathVariable int id) {
        subjectService.deleteSubject(id);
        return "redirect:/index";
    }
    
    @PostMapping("updateSubjectCategory/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String updateSubjectCategory(@PathVariable String id) {
        subjectService.updateSubjectWeb(id);
        return "redirect:/index";
    }
    
}
