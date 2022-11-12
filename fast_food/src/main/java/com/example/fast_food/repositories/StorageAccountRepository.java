package com.example.fast_food.repositories;

import com.example.fast_food.entities.ImageAccount;
import com.example.fast_food.entities.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StorageAccountRepository extends JpaRepository<ImageAccount, Long> {
    Optional<ImageAccount> findByImageName(String fileName);

    @Modifying
    @Transactional
    @Query("UPDATE ImageAccount i SET i.imageData=:imageData,i.type=:type WHERE i.imageAccountId=:id")
    void updateImage(@Param("imageData") byte[] imageData, @Param("type") String type, @Param("id") long id);
}
