package com.test.token.jwtoken.entities.page;

import com.test.token.jwtoken.entities.modules.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Data <T>{

    private int pageNumber;
    private int pageSize;
    private int maxAvailablePage;
    private List<Post> posts;

}
