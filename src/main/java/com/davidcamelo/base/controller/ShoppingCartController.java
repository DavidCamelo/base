package com.davidcamelo.base.controller;

import com.davidcamelo.base.dto.ProductDTO;
import com.davidcamelo.base.dto.ShoppingCartDTO;
import com.davidcamelo.base.io.entity.ShoppingCart;
import com.davidcamelo.base.service.BaseService;
import com.davidcamelo.base.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.davidcamelo.base.util.Constants.ADD_PRODUCT;
import static com.davidcamelo.base.util.Constants.REMOVE_PRODUCT;
import static com.davidcamelo.base.util.Constants.SHOPPING_CART;
import static com.davidcamelo.base.util.Constants.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(SHOPPING_CART)
public class ShoppingCartController extends BaseController<ShoppingCart, ShoppingCartDTO> {
    private final ShoppingCartService shoppingCartService;

    @PutMapping(path = UUID + ADD_PRODUCT)
    public ResponseEntity<ShoppingCartDTO> addProduct(@PathVariable String uuid, @RequestBody ProductDTO productDTO) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.addProduct(uuid, productDTO);
        return new ResponseEntity<>(shoppingCartDTO, HttpStatus.OK);
    }

    @PutMapping(path = UUID + REMOVE_PRODUCT)
    public ResponseEntity<ShoppingCartDTO> removeProduct(@PathVariable String uuid, @RequestBody ProductDTO productDTO) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.removeProduct(uuid, productDTO);
        return new ResponseEntity<>(shoppingCartDTO, HttpStatus.OK);
    }

    @Override
    public BaseService<ShoppingCart, ShoppingCartDTO> getService() {
        return shoppingCartService;
    }
}
