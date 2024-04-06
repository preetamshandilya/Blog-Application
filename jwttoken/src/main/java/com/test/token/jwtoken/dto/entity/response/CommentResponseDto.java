package com.test.token.jwtoken.dto.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private Integer commentId;

    private Integer commentByUserId;

    private String commentByUserName;

    private String content;

    private LocalDateTime createdAt;

}
