package com.test.token.jwtoken.repositories;

import com.test.token.jwtoken.entities.modules.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
