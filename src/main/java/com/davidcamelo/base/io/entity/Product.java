package com.davidcamelo.base.io.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseEntity {
    private String name;
    private String description;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_UUID")
    private Category category;
    @ToString.Exclude
    @ManyToMany(mappedBy = "products")
    private Set<ShoppingCart> shoppingCarts;
    @Transient
    private Long count;
}
