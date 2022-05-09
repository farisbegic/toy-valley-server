package com.toyvalley.controllers;

import com.toyvalley.models.data.category.CategoryResponse;
import com.toyvalley.models.data.category.CreateCategoryRequest;
import com.toyvalley.models.data.category.UpdateCategoryRequest;
import com.toyvalley.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return new ResponseEntity<>(this.categoryService.getCategories(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable long id) {
        return new ResponseEntity<>(this.categoryService.getCategory(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CreateCategoryRequest category) {
        return new ResponseEntity<>(this.categoryService.createCategory(category), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable long id, @RequestBody UpdateCategoryRequest category) {
        CategoryResponse response = this.categoryService.updateCategory(id, category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable long id) {
        this.categoryService.deleteCategory(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}