package com.davidcamelo.base.io.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart extends BaseEntity {
    private Long count = 0L;
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SHOPPING_CART_PRODUCT",
            joinColumns = @JoinColumn(
                    name = "SHOPPING_CART_UUID", referencedColumnName = "UUID"),
            inverseJoinColumns = @JoinColumn(
                    name = "PRODUCT_UUID", referencedColumnName = "UUID"))
    private Set<Product> products;
}
