package com.vtyurin.repository;

import com.vtyurin.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.parent = ?1")
    List<Category> findByParentId(Long id);
}
