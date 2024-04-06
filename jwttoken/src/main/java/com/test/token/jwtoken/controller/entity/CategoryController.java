package com.test.token.jwtoken.controller.entity;


import com.test.token.jwtoken.dto.entity.request.CategoryRequestDto;
import com.test.token.jwtoken.dto.entity.response.CategoryResponseDto;
import com.test.token.jwtoken.services.entity.serviceInterface.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/value")
    ResponseEntity<CategoryResponseDto> createCategoryHandler(
            @Valid @RequestBody CategoryRequestDto categoryRequestDto) {

        CategoryResponseDto category = categoryService.createCategory(categoryRequestDto);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
