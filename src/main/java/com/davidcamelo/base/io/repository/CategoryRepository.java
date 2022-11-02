package com.davidcamelo.base.io.repository;

import com.davidcamelo.base.io.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, String> {
}
