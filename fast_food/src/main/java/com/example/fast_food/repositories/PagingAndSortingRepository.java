package com.example.fast_food.repositories;

import com.example.fast_food.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PagingAndSortingRepository<T,id> {

}
