package com.davidcamelo.base.controller;

import com.davidcamelo.base.dto.BaseDTO;
import com.davidcamelo.base.dto.FilterDTO;
import com.davidcamelo.base.io.entity.BaseEntity;
import com.davidcamelo.base.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.davidcamelo.base.util.Constants.UUID;

public abstract class BaseController<T extends BaseEntity, V extends BaseDTO> {

    @PostMapping
    public ResponseEntity<V> create(@RequestBody V baseDTO) {
        baseDTO = getService().create(baseDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping(path = UUID)
    public ResponseEntity<V> getByUuid(@PathVariable String uuid) {
        V baseDTO = getService().get(uuid);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<V>> getAll(FilterDTO filterDTO) {
        Page<V> page = getService().getAll(filterDTO);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<V> update(@RequestBody V baseDTO) {
        baseDTO = getService().update(baseDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<V> delete(@RequestBody V baseDTO) {
        baseDTO = getService().delete(baseDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }

    public abstract BaseService<T, V> getService();
}
