package com.test.token.jwtoken.dto.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {

    private Integer categoryId;

    private String categoryTitle;

    private String categoryDescription;

}
