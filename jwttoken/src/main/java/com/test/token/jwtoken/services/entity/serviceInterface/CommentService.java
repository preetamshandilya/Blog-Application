package com.test.token.jwtoken.services.entity.serviceInterface;

import com.test.token.jwtoken.dto.entity.request.CommentRequestDto;
import com.test.token.jwtoken.dto.entity.response.CommentResponseDto;
import com.test.token.jwtoken.exception.ResourceNotFoundException;

public interface CommentService {

    CommentResponseDto createComment(CommentRequestDto commentRequestDto,Integer postId, Integer userId) throws ResourceNotFoundException;
}
