package com.davidcamelo.base.io.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "SHOPPING_CART_PRODUCT")
@IdClass(ShoppingCartProductId.class)
public class ShoppingCartProduct {
    @Id
    @Column(name = "SHOPPING_CART_UUID")
    private String shoppingCartUuid;
    @Id
    @Column(name = "PRODUCT_UUID")
    private String productUuid;
    private Long count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShoppingCartProduct that = (ShoppingCartProduct) o;
        return shoppingCartUuid != null && Objects.equals(shoppingCartUuid, that.shoppingCartUuid)
                && productUuid != null && Objects.equals(productUuid, that.productUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCartUuid, productUuid);
    }
}
