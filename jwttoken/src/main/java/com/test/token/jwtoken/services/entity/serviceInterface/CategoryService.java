package com.test.token.jwtoken.services.entity.serviceInterface;

import com.test.token.jwtoken.dto.entity.request.CategoryRequestDto;
import com.test.token.jwtoken.dto.entity.response.CategoryResponseDto;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

}
