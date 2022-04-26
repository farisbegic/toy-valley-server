package com.toyvalley.controllers;

import com.toyvalley.models.entities.Category;
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
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(this.categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id) {
        return new ResponseEntity<>(this.categoryService.getCategory(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category response = this.categoryService.createCategory(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category category) {
        Category response = this.categoryService.updateCategory(id, category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCategory(@PathVariable long id) {
        if (this.categoryService.deleteCategory(id)) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}