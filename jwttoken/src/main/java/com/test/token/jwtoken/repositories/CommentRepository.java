package com.test.token.jwtoken.repositories;

import com.test.token.jwtoken.entities.modules.Comment;
import com.test.token.jwtoken.entities.modules.Post;
import com.test.token.jwtoken.entities.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findByUser(User user);

    List<Comment> findByPost(Post post);
}
