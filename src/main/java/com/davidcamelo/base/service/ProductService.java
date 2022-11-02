package com.davidcamelo.base.service;

import com.davidcamelo.base.dto.FilterDTO;
import com.davidcamelo.base.dto.ProductDTO;
import com.davidcamelo.base.io.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService extends BaseService<Product, ProductDTO> {
    Page<ProductDTO> getProductsByCategory(String uuid, FilterDTO filterDTO);
}
