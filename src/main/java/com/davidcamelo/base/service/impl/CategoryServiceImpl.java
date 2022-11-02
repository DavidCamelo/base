package com.davidcamelo.base.service.impl;

import com.davidcamelo.base.dto.CategoryDTO;
import com.davidcamelo.base.io.entity.Category;
import com.davidcamelo.base.io.repository.BaseRepository;
import com.davidcamelo.base.io.repository.CategoryRepository;
import com.davidcamelo.base.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryDTO> implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public BaseRepository<Category, String> getRepository() {
        return categoryRepository;
    }
}
