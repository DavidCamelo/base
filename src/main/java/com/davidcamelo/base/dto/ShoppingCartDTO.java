package com.davidcamelo.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCartDTO extends BaseDTO {
    private Long count;
    private Collection<ProductDTO> products;
}
