package com.davidcamelo.base.service.impl;

import com.davidcamelo.base.dto.ProductDTO;
import com.davidcamelo.base.dto.ShoppingCartDTO;
import com.davidcamelo.base.io.entity.Product;
import com.davidcamelo.base.io.entity.ShoppingCart;
import com.davidcamelo.base.io.entity.ShoppingCartProduct;
import com.davidcamelo.base.io.entity.ShoppingCartProductId;
import com.davidcamelo.base.io.repository.BaseRepository;
import com.davidcamelo.base.io.repository.ShoppingCartProductRepository;
import com.davidcamelo.base.io.repository.ShoppingCartRepository;
import com.davidcamelo.base.service.ProductService;
import com.davidcamelo.base.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ShoppingCartServiceImpl extends BaseServiceImpl<ShoppingCart, ShoppingCartDTO> implements ShoppingCartService {
    private final ProductService productService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartProductRepository shoppingCartProductRepository;

    @Override
    public void getLogic(ShoppingCart shoppingCart) {
        super.getLogic(shoppingCart);
        shoppingCart.getProducts().forEach(product -> {
            Optional<ShoppingCartProduct> oShoppingCartProduct = getShoppingCartProductId(shoppingCart.getUuid(), product.getUuid());
            oShoppingCartProduct.ifPresent(shoppingCartProduct -> product.setCount(shoppingCartProduct.getCount()));
        });
    }

    @Override
    public ShoppingCartDTO addProduct(String uuid, ProductDTO productDTO) {
        ShoppingCart shoppingCart = "new".equals(uuid) ? new ShoppingCart() : getByUUID(uuid);
        Product product = productService.getByUUID(productDTO.getUuid());
        if (shoppingCart.getProducts() == null) {
            shoppingCart.setProducts(new HashSet<>());
        }
        shoppingCart.getProducts().add(product);
        shoppingCart.setCount(shoppingCart.getCount() + 1L);
        shoppingCartRepository.save(shoppingCart);
        Optional<ShoppingCartProduct> oShoppingCartProduct = getShoppingCartProductId(shoppingCart.getUuid(), product.getUuid());
        oShoppingCartProduct.ifPresent(shoppingCartProduct -> {
            if (shoppingCartProduct.getCount() == null) {
                shoppingCartProduct.setCount(0L);
            }
            shoppingCartProduct.setCount(shoppingCartProduct.getCount() + 1L);
            product.setCount(shoppingCartProduct.getCount());
            shoppingCartProductRepository.save(shoppingCartProduct);
        });
        return modelMapper.map(shoppingCart, ShoppingCartDTO.class);
    }

    @Override
    public ShoppingCartDTO removeProduct(String uuid, ProductDTO productDTO) {
        ShoppingCart shoppingCart = getByUUID(uuid);
        Product product = productService.getByUUID(productDTO.getUuid());
        if (shoppingCart.getProducts().contains(product)) {
            Optional<ShoppingCartProduct> oShoppingCartProduct = getShoppingCartProductId(shoppingCart.getUuid(), product.getUuid());
            oShoppingCartProduct.ifPresent(shoppingCartProduct -> {
                if (shoppingCartProduct.getCount().equals(1L)) {
                    shoppingCart.getProducts().remove(product);
                } else {
                    shoppingCartProduct.setCount(shoppingCartProduct.getCount() - 1L);
                    product.setCount(shoppingCartProduct.getCount());
                    shoppingCartProductRepository.save(shoppingCartProduct);
                }
                shoppingCart.setCount(shoppingCart.getCount() - 1L);
            });
        }
        shoppingCartRepository.save(shoppingCart);
        return modelMapper.map(shoppingCart, ShoppingCartDTO.class);
    }

    private Optional<ShoppingCartProduct> getShoppingCartProductId(String shoppingCartUuid, String productUuid) {
        ShoppingCartProductId shoppingCartProductId = new ShoppingCartProductId();
        shoppingCartProductId.setShoppingCartUuid(shoppingCartUuid);
        shoppingCartProductId.setProductUuid(productUuid);
        return shoppingCartProductRepository.findById(shoppingCartProductId);
    }

    @Override
    public BaseRepository<ShoppingCart, String> getRepository() {
        return shoppingCartRepository;
    }
}
