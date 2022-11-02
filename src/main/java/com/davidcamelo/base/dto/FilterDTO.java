package com.davidcamelo.base.dto;

import com.davidcamelo.base.enums.StateEnum;
import lombok.Data;
import org.springframework.data.domain.Sort.Direction;

@Data
public class FilterDTO {
    private int page = 1;
    private int limit = 10;
    private Direction direction = Direction.ASC;
    private String orderBy = "uuid";
    private StateEnum state = StateEnum.ACTIVE;
}
