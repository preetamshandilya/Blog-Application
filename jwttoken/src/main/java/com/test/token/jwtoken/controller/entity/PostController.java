package com.test.token.jwtoken.controller.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.token.jwtoken.dto.entity.request.PostRequestDto;
import com.test.token.jwtoken.dto.entity.response.PostResponseDto;
import com.test.token.jwtoken.entities.modules.Post;
import com.test.token.jwtoken.entities.page.PostPage;
import com.test.token.jwtoken.exception.FileTypeNotValidException;
import com.test.token.jwtoken.exception.ResourceNotFoundException;
import com.test.token.jwtoken.services.entity.serviceInterface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")

public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostResponseDto> createPostHandler(@RequestBody PostRequestDto postRequestDto,
                                                             @PathVariable("userId") Integer userId,
                                                             @PathVariable("categoryId") Integer categoryId)

            throws ResourceNotFoundException, IOException{

        PostResponseDto createPost = postService.createPostWithoutFile(postRequestDto, userId, categoryId);

        return new ResponseEntity<PostResponseDto>(createPost, HttpStatus.CREATED);
    }

    @PostMapping("/file/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostResponseDto> createPostHandler(@RequestParam String postRequestDto,
                                                             @PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId,
                                                             @RequestParam MultipartFile image)
            throws ResourceNotFoundException, IOException, FileTypeNotValidException {

        // converting String into JSON
        PostRequestDto post = objectMapper.readValue(postRequestDto, PostRequestDto.class);

        PostResponseDto createPost = postService.createPost(post, userId, categoryId, image);

        return new ResponseEntity<PostResponseDto>(createPost, HttpStatus.CREATED);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<PostPage<Post>> readPage(@RequestParam(value = "offset",defaultValue = "0") int offset,
                                                       @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                                       @RequestParam(value = "sortBy",defaultValue = "postId") String sortBy,
                                                       @RequestParam(value = "sortOrder",defaultValue = "asc") String sortOrder,
                                                       @RequestParam(required = false) List<String> authorFilter,
                                                       @RequestParam(required = false) List<String> categoryFilter)
    {
        PostPage<Post> page = this.postService.page(offset,pageSize,sortBy,sortOrder,authorFilter,categoryFilter);
        return new ResponseEntity<>(page,HttpStatus.OK);
    }

}
