package com.davidcamelo.base.service;

import com.davidcamelo.base.dto.BaseDTO;
import com.davidcamelo.base.dto.FilterDTO;
import com.davidcamelo.base.io.entity.BaseEntity;
import org.springframework.data.domain.Page;

public interface BaseService<T extends BaseEntity, V extends BaseDTO> {
    V create(V baseDTO);

    V get(String uuid);

    Page<V> getAll(FilterDTO filterDTO);

    T getByUUID(String uuid);

    V update(V baseDTO);

    V delete(V baseDTO);
}
