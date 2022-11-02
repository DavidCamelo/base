package com.davidcamelo.base.io.repository;

import com.davidcamelo.base.io.entity.ShoppingCartProduct;
import com.davidcamelo.base.io.entity.ShoppingCartProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, ShoppingCartProductId> {
}
