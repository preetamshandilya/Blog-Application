package com.test.token.jwtoken.entities.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PostPage<T> {

    private Data<T> data;
    private HttpStatus<T> status;


}
