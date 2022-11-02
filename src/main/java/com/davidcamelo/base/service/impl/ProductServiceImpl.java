package com.davidcamelo.base.service.impl;

import lombok.AllArgsConstructor;
import com.davidcamelo.base.dto.CategoryDTO;
import com.davidcamelo.base.dto.FilterDTO;
import com.davidcamelo.base.dto.ProductDTO;
import com.davidcamelo.base.enums.StateEnum;
import com.davidcamelo.base.io.entity.Category;
import com.davidcamelo.base.io.entity.Product;
import com.davidcamelo.base.io.repository.BaseRepository;
import com.davidcamelo.base.io.repository.ProductRepository;
import com.davidcamelo.base.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductDTO> implements ProductService {
    private final CategoryServiceImpl categoryService;
    private final ProductRepository productRepository;

    @Override
    public void createLogic(Product product, ProductDTO productDTO) {
        CategoryDTO categoryDTO = productDTO.getCategory();
        if (categoryDTO != null && categoryDTO.getUuid() != null) {
            Category category = categoryService.getByUUID(categoryDTO.getUuid());
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }
    }

    @Override
    public void updateLogic(Product baseEntity, ProductDTO productDTO) {
        createLogic(baseEntity, productDTO);
    }

    @Override
    public Page<ProductDTO> getProductsByCategory(String uuid, FilterDTO filterDTO) {
        Category category = categoryService.getByUUID(uuid);
        return mapPage(productRepository.findAllByCategoryAndState(category, StateEnum.ACTIVE, getPageRequest(filterDTO)));
    }

    @Override
    public BaseRepository<Product, String> getRepository() {
        return productRepository;
    }
}
