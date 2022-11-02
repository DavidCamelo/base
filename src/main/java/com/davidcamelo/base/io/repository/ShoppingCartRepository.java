package com.davidcamelo.base.io.repository;

import com.davidcamelo.base.io.entity.ShoppingCart;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends BaseRepository<ShoppingCart, String> {
}
