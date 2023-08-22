/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.controllers;

import com.example.taptry.models.Category;
import com.example.taptry.service.CategoryService;
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
@RequestMapping("/api/category")
public class CategoryController {
    
    private final CategoryService categoryService;
    
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    
    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    
    public List<Category> getCategoriesList(){
        List<Category> categories = categoryService.getAllCategories();
        return categories;
    }
    
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id){
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody Category categoryDetail){
        Category updatedCategory = categoryService.updateCategory(id, categoryDetail);
        return ResponseEntity.ok(updatedCategory);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
