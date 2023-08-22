/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.taptry.service;

import com.example.taptry.models.Category;
import com.example.taptry.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author becerraavilamauricio
 */
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    
    CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
    
    public Category getCategoryById(String id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
    
    public Category updateCategory (String id, Category categoryDetail){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));
        
        category.setMinTotalPoints(categoryDetail.getMinTotalPoints());
        category.setName(categoryDetail.getName());
        
        return categoryRepository.save(category);
    }
    
    public void deleteCategory(String id){
        categoryRepository.deleteById(id);
    }

    public int getCategoryMinimumScoreById(String categoryId) {
        Category category = this.getCategoryById(categoryId);
        return category.getMinTotalPoints();
    }
    
    public List getAvailableCategories() {
        List<String> categAvailable = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            if(found(c) == false)
                categAvailable.add(c+"");
        }
        return categAvailable;
    }

    private boolean found(char c) {
        try{
            this.getCategoryById(c+"");
        }catch(IllegalArgumentException e){
            return false;
        }
        return true;
    }

    public void createCategoryWeb(String categoryId, String categoryName, String categoryScore) {
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        category.setMinTotalPoints(Integer.parseInt(categoryScore));
        categoryRepository.save(category);
    }
    
    
}
