package com.davidcamelo.base.controller;

import com.davidcamelo.base.dto.ProductDTO;
import com.davidcamelo.base.io.entity.Product;
import com.davidcamelo.base.service.BaseService;
import com.davidcamelo.base.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.davidcamelo.base.util.Constants.PRODUCT;

@AllArgsConstructor
@RestController
@RequestMapping(PRODUCT)
public class ProductController extends BaseController<Product, ProductDTO> {
    private final ProductService productService;

    @Override
    public BaseService<Product, ProductDTO> getService() {
        return productService;
    }
}
