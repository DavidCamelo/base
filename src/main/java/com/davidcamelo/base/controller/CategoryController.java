package com.davidcamelo.base.controller;

import com.davidcamelo.base.dto.CategoryDTO;
import com.davidcamelo.base.dto.FilterDTO;
import com.davidcamelo.base.dto.ProductDTO;
import com.davidcamelo.base.io.entity.Category;
import com.davidcamelo.base.service.BaseService;
import com.davidcamelo.base.service.CategoryService;
import com.davidcamelo.base.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.davidcamelo.base.util.Constants.CATEGORY;
import static com.davidcamelo.base.util.Constants.PRODUCT;
import static com.davidcamelo.base.util.Constants.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(CATEGORY)
public class CategoryController extends BaseController<Category, CategoryDTO> {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping(path = UUID + PRODUCT)
    public ResponseEntity<Page<ProductDTO>> getProductsByCategory(@PathVariable String uuid, FilterDTO filterDTO) {
        Page<ProductDTO> productsByCategory = productService.getProductsByCategory(uuid, filterDTO);
        return new ResponseEntity<>(productsByCategory, HttpStatus.OK);
    }

    @Override
    public BaseService<Category, CategoryDTO> getService() {
        return categoryService;
    }
}
