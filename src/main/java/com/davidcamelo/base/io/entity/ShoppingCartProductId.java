package com.davidcamelo.base.io.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShoppingCartProductId implements Serializable {
    private String shoppingCartUuid;
    private String productUuid;
}
