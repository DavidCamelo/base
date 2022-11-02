package com.davidcamelo.base.dto;

import com.davidcamelo.base.enums.StateEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"uuid"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseDTO {
    private String uuid;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StateEnum state;
}
