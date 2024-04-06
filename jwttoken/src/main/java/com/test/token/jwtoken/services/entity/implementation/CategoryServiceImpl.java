package com.test.token.jwtoken.services.entity.implementation;

import com.test.token.jwtoken.dto.entity.request.CategoryRequestDto;
import com.test.token.jwtoken.dto.entity.response.CategoryResponseDto;
import com.test.token.jwtoken.entities.modules.Category;
import com.test.token.jwtoken.repositories.CategoryRepository;
import com.test.token.jwtoken.services.entity.serviceInterface.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = this.toCategory(categoryRequestDto);

        Category saved_category = this.categoryRepository.save(category);

        return this.toCategoryDto(saved_category);
    }

    public Category toCategory(CategoryRequestDto categoryRequestDto) {

        return modelMapper.map(categoryRequestDto, Category.class);

    }

    public CategoryResponseDto toCategoryDto(Category category) {


        return modelMapper.map(category, CategoryResponseDto.class);

    }
}
