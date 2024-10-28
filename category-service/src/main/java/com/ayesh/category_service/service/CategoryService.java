package com.ayesh.category_service.service;

import com.ayesh.category_service.dao.CategoryDao;
import com.ayesh.category_service.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> addCategory(Category category) {
        try {
            categoryDao.save(category);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Category> getCategoryById(int id) {
        return new ResponseEntity<>(categoryDao.findById(id).get(),HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCategory(int id) {
        categoryDao.deleteById(id);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    public ResponseEntity<Category> updateCategory(int id, Category category) {

        Category existingCategory = categoryDao.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        existingCategory.setName(category.getName());
        Category updatedCategory = categoryDao.save(existingCategory);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }
}
