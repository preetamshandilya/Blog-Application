package com.test.token.jwtoken.services.entity.serviceInterface;

import com.test.token.jwtoken.dto.entity.request.PostRequestDto;
import com.test.token.jwtoken.dto.entity.response.PostResponseDto;
import com.test.token.jwtoken.entities.modules.Post;
import com.test.token.jwtoken.entities.page.PostPage;
import com.test.token.jwtoken.exception.FileTypeNotValidException;
import com.test.token.jwtoken.exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostResponseDto createPost(PostRequestDto postRequestDto, Integer userId, Integer categoryId, MultipartFile image)
            throws ResourceNotFoundException, IOException, FileTypeNotValidException;

    PostPage<Post> page(int offset, int pageSize, String sortBy, String sortOrder, List<String> authorFilter, List<String> categoryFilter);

    PostResponseDto createPostWithoutFile(PostRequestDto postRequestDto, Integer userId, Integer categoryId) throws ResourceNotFoundException;
}
