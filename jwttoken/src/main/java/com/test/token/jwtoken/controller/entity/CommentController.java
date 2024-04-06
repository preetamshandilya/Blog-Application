package com.test.token.jwtoken.controller.entity;

import com.test.token.jwtoken.dto.entity.request.CommentRequestDto;
import com.test.token.jwtoken.dto.entity.response.CommentResponseDto;
import com.test.token.jwtoken.exception.ResourceNotFoundException;
import com.test.token.jwtoken.services.entity.serviceInterface.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")

public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentResponseDto> createComment(
            @Valid @RequestBody CommentRequestDto commentRequestDto,Integer postId,Integer userId) throws ResourceNotFoundException {

        CommentResponseDto comment = this.commentService.createComment(commentRequestDto,postId,userId);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}
