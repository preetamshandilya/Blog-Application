package com.test.token.jwtoken.repositories;

import com.test.token.jwtoken.entities.modules.Category;
import com.test.token.jwtoken.entities.modules.Post;
import com.test.token.jwtoken.entities.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post,Integer>, JpaSpecificationExecutor<Post> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    // Searching By Title
    List<Post> findByTitleContaining(String title);
}
