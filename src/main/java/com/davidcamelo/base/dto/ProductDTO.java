package com.davidcamelo.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO extends BaseDTO {
    private String name;
    private String description;
    private Double price;
    private CategoryDTO category;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long count;
}
