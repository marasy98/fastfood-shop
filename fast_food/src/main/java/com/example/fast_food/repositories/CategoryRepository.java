package com.example.fast_food.repositories;

import com.example.fast_food.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT c from Category c where c.categoryName=:name")
    Category findByCategory(@Param("name") String name);
    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.categoryName=:categoryName WHERE c.categoryId=:categoryId")
    void updateCategory(@Param("categoryId") long categoryId,@Param("categoryName") String categoryName);
    @Modifying
    @Transactional
    void deleteByCategoryId(long id);
}
