package com.test.token.jwtoken.services.entity.implementation;

import com.test.token.jwtoken.dto.entity.request.PostRequestDto;
import com.test.token.jwtoken.dto.entity.response.PostResponseDto;
import com.test.token.jwtoken.entities.modules.Category;
import com.test.token.jwtoken.entities.modules.Post;
import com.test.token.jwtoken.entities.modules.User;
import com.test.token.jwtoken.entities.page.Data;
import com.test.token.jwtoken.entities.page.HttpStatus;
import com.test.token.jwtoken.entities.page.PostPage;
import com.test.token.jwtoken.exception.FileTypeNotValidException;
import com.test.token.jwtoken.exception.ResourceNotFoundException;
import com.test.token.jwtoken.payloads.ImageResponse;
import com.test.token.jwtoken.repositories.CategoryRepository;
import com.test.token.jwtoken.repositories.PostRepository;
import com.test.token.jwtoken.repositories.UserRepository;
import com.test.token.jwtoken.services.entity.serviceInterface.FileService;
import com.test.token.jwtoken.services.entity.serviceInterface.PostService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileService fileService;


    @Value("${project.image}")
    private String path;

    @Override
    public PostResponseDto createPost(PostRequestDto postRequestDto, Integer userId, Integer categoryId,
                                      MultipartFile image) throws ResourceNotFoundException, IOException, FileTypeNotValidException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        if (image == null) {

            Post post = this.toPost(postRequestDto);
            post.setCreatedAt(LocalDateTime.now());
            post.setUser(user);
            post.setCategory(category);
            post.setImage(path);

            Post savedPost = this.postRepository.save(post);

            return this.toPostDto(savedPost);

        } else {

            ImageResponse uploadImage = fileService.updatePostImage(path, image);

            Post post = this.toPost(postRequestDto);
            post.setCreatedAt(LocalDateTime.now());
            post.setUser(user);
            post.setCategory(category);
            post.setImage(uploadImage.getFileName());

            Post savedPost = this.postRepository.save(post);

            return this.toPostDto(savedPost);
        }

    }

    @Override
    public PostPage<Post> page(int offset, int pageSize, String sortBy, String sortOrder, List<String> authorFilter, List<String> categoryFilter) {
        try {
            Sort.Direction direction = Sort.Direction.ASC;
            if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
                direction.isDescending();
            }

            Specification<Post> specification = (root, query, criteriaBuilder) -> {
                Predicate predicate = criteriaBuilder.conjunction();

                if (authorFilter != null && !authorFilter.isEmpty()) {
                    for (String author : authorFilter) {
                        Join<Post, User> userJoin = root.join("user");
                        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userJoin.get("name"), "%" + author + "%"));
                    }
                }

                if (categoryFilter != null && !categoryFilter.isEmpty()) {
                    for (String category : categoryFilter) {
                        Join<Post, Category> userJoin = root.join("category");
                        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userJoin.get("categoryTitle"), "%" + category + "%"));
                    }
                }
                return predicate;
            };

            Page<Post> page = postRepository.findAll(specification, PageRequest.of(offset, pageSize, direction, sortBy));

            PostPage<Post> postPage = new PostPage<>();

            HttpStatus<Post> httpStatus = new HttpStatus<>();
            httpStatus.setStatusCode(200);
            httpStatus.setStatusMessage("Success!!");
            postPage.setStatus(httpStatus);

            Data<Post> postData = new Data<>();
            postData.setPosts(page.getContent());
            postData.setPageSize(page.getSize());
            postData.setPageNumber(page.getNumber()+1);
            postData.setMaxAvailablePage(page.getTotalPages());

            postPage.setData(postData);
            return postPage;

        }catch (Exception e){
            PostPage<Post> errorPage = new PostPage<>();
            HttpStatus<Post> httpStatus = new HttpStatus<>();
            httpStatus.setStatusCode(500);
            httpStatus.setStatusMessage("Error: " + e.getMessage());

            errorPage.setStatus(httpStatus);
            System.out.println("error");
            return errorPage;
        }
    }

    @Override
    public PostResponseDto createPostWithoutFile(PostRequestDto postRequestDto, Integer userId, Integer categoryId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = this.toPost(postRequestDto);
        post.setUser(user);
        post.setCategory(category);
        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = this.postRepository.save(post);
        return this.toPostDto(savedPost);
    }

    // utility methods
    Post toPost(PostRequestDto postRequestDto) {

        return this.modelMapper.map(postRequestDto, Post.class);
    }

    PostResponseDto toPostDto(Post post) {

        return this.modelMapper.map(post, PostResponseDto.class);
    }
}


