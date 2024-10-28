package com.ayesh.category_service.controller;

import com.ayesh.category_service.model.Category;
import com.ayesh.category_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("allCategories")
    public ResponseEntity<List<Category>> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @PostMapping("add")
    public ResponseEntity<String> addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody Category category){
        return categoryService.updateCategory(id,category);
    }
}
