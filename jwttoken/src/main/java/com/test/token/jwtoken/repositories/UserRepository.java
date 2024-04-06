package com.test.token.jwtoken.repositories;

import com.test.token.jwtoken.entities.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findFirstByEmail(String email);

    Boolean existsByEmail(String email);

    List<User> findByNameContaining(String keyword);

    public Optional<User> findByEmail(String email);


}
