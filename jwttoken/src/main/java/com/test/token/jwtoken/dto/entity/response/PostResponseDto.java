package com.test.token.jwtoken.dto.entity.response;

import com.test.token.jwtoken.dto.auth.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private Integer postId;

    private String title;

    private String contentLine1;

    private String contentLine2;

    private String image;

    private LocalDateTime createdAt;

    private CategoryResponseDto category;

    private UserResponseDto user;

    private List<CommentResponseDto> comments;

}
