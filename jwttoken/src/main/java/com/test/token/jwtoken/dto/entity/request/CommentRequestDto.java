package com.test.token.jwtoken.dto.entity.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {

    @NotNull(message = "{Comment.field.invalid}")
    @NotBlank(message = "{Comment.field.invalid}")
    @NotEmpty(message = "{Comment.field.invalid}")
    private String content;

}
