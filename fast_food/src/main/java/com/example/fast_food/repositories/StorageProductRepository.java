package com.example.fast_food.repositories;

import com.example.fast_food.entities.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface StorageProductRepository extends JpaRepository<ImageProduct, Long> {
    Optional<ImageProduct> findByImageName(String fileName);

    @Query("SELECT imageName FROM ImageProduct ")
    List<String> findAllName();

    @Transactional
    void deleteByImageName(String name);
    @Modifying
    @Transactional
    @Query("UPDATE ImageProduct i SET i.type=:type,i.imageData=:imageData WHERE i.imageName=:name")
    void updateImage(@Param("type") String type,@Param("imageData") byte[] imageData,@Param("name") String name);
}
