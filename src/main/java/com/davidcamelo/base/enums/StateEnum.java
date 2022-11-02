package com.davidcamelo.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateEnum {
    ACTIVE("state.active"),
    INACTIVE("state.inactive");
    private final String key;
}
