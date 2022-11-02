package com.davidcamelo.base.io.repository;

import com.davidcamelo.base.enums.StateEnum;
import com.davidcamelo.base.io.entity.Category;
import com.davidcamelo.base.io.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, String> {
    Page<Product> findAllByCategoryAndState(Category category, StateEnum state, Pageable pageable);
}
