package com.test.token.jwtoken.services.entity.implementation;

import com.test.token.jwtoken.dto.entity.request.CommentRequestDto;
import com.test.token.jwtoken.dto.entity.response.CommentResponseDto;
import com.test.token.jwtoken.entities.modules.Comment;
import com.test.token.jwtoken.entities.modules.Post;
import com.test.token.jwtoken.entities.modules.User;
import com.test.token.jwtoken.exception.ResourceNotFoundException;
import com.test.token.jwtoken.repositories.CommentRepository;
import com.test.token.jwtoken.repositories.PostRepository;
import com.test.token.jwtoken.repositories.UserRepository;
import com.test.token.jwtoken.services.entity.serviceInterface.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto,Integer postId,Integer userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        Comment comment = this.toComment(commentRequestDto);
        Comment saved_comment = this.commentRepository.save(comment);

        return this.toCommentResponseDto(saved_comment);

    }

    CommentResponseDto toCommentResponseDto(Comment comment) {

        return this.modelMapper.map(comment, CommentResponseDto.class);
    }

    Comment toComment(CommentRequestDto commentRequestDto) {

        return this.modelMapper.map(commentRequestDto, Comment.class);

    }
}
