package com.test.token.jwtoken.dto.entity.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {

    @NotNull(message = "{Post.title.invalid}")
    @NotBlank(message = "{Post.title.invalid}")
    @NotEmpty(message = "{Post.title.invalid}")
    private String title;

    @NotNull(message = "{Post.content.invalid}")
    @NotBlank(message = "{Post.content.invalid}")
    @NotEmpty(message = "{Post.content.invalid}")
    @Column(length = Integer.MAX_VALUE)
    private String contentLine1;

    @NotNull(message = "{Post.content.invalid}")
    @NotBlank(message = "{Post.content.invalid}")
    @NotEmpty(message = "{Post.content.invalid}")
    @Column(length = Integer.MAX_VALUE)
    private String contentLine2;

}
