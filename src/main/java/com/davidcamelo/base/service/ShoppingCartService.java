package com.davidcamelo.base.service;

import com.davidcamelo.base.dto.ProductDTO;
import com.davidcamelo.base.dto.ShoppingCartDTO;
import com.davidcamelo.base.io.entity.ShoppingCart;

public interface ShoppingCartService extends BaseService<ShoppingCart, ShoppingCartDTO> {
    ShoppingCartDTO addProduct(String uuid, ProductDTO productDTO);

    ShoppingCartDTO removeProduct(String uuid, ProductDTO productDTO);
}
