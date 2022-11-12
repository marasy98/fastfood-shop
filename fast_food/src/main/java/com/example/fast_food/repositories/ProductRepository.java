package com.example.fast_food.repositories;

import com.example.fast_food.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(long v);

    Product findByProductName(String name);
    @Transactional
    void deleteByProductName(String name);

    @Query("SELECT p from Product p WHERE p.productName like :search")
    Page<Product> findByProductName(@Param("search") String search, Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.category.categoryId=:categoryId, p.price=:price,p.description=:description,p.quantity=:quantity WHERE p.productName=:name")
    void updateProduct(@Param("categoryId") long categoryId,@Param("price") int price, @Param("description") String description, @Param("name") String name,@Param("quantity") int quantity);



}
