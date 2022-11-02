package com.davidcamelo.base.service.impl;

import com.davidcamelo.base.dto.BaseDTO;
import com.davidcamelo.base.dto.FilterDTO;
import com.davidcamelo.base.enums.StateEnum;
import com.davidcamelo.base.exception.ServiceException;
import com.davidcamelo.base.io.entity.BaseEntity;
import com.davidcamelo.base.io.repository.BaseRepository;
import com.davidcamelo.base.service.BaseService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.davidcamelo.base.util.Constants.RECORD_NOT_FOUND;

public abstract class BaseServiceImpl<T extends BaseEntity, V extends BaseDTO> implements BaseService<T, V> {
    protected final ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    @Override
    public V create(V baseDTO) {
        T baseEntity = modelMapper.map(baseDTO, typeEntity());
        createLogic(baseEntity, baseDTO);
        getRepository().save(baseEntity);
        return modelMapper.map(baseEntity, typeDTO());
    }

    public void createLogic(T baseEntity, V baseDTO) {
    }

    @Override
    public V get(String uuid) {
        return modelMapper.map(getByUUID(uuid), typeDTO());
    }

    public void getLogic(T baseEntity) {
    }

    @Override
    public Page<V> getAll(FilterDTO filterDTO) {
        return mapPage(getRepository().findAllByState(filterDTO.getState(), getPageRequest(filterDTO)));
    }

    protected PageRequest getPageRequest(FilterDTO filterDTO) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(filterDTO.getDirection(), "string".equalsIgnoreCase(filterDTO.getOrderBy()) ? "uuid" : filterDTO.getOrderBy()));
        return PageRequest.of(filterDTO.getPage() <= 0 ? 0 : filterDTO.getPage() - 1, filterDTO.getLimit() <= 0 ? 10 : filterDTO.getLimit(), Sort.by(orders));
    }

    protected Page<V> mapPage(Page<T> page) {
        return page.map(baseEntity -> {
            getLogic(baseEntity);
            return modelMapper.map(baseEntity, typeDTO());
        });
    }

    @Override
    public T getByUUID(String uuid) {
        Optional<T> oBaseEntity = getRepository().findByUuid(uuid);
        T baseEntity = oBaseEntity.orElseThrow(() -> new ServiceException(RECORD_NOT_FOUND));
        getLogic(baseEntity);
        return baseEntity;
    }

    @Override
    public V update(V baseDTO) {
        T baseEntity = getByUUID(baseDTO.getUuid());
        modelMapper.map(baseDTO, baseEntity);
        updateLogic(baseEntity, baseDTO);
        getRepository().save(baseEntity);
        return modelMapper.map(baseEntity, typeDTO());
    }

    public void updateLogic(T baseEntity, V baseDTO) {
    }

    @Override
    public V delete(V baseDTO) {
        T baseEntity = getByUUID(baseDTO.getUuid());
        baseEntity.setState(StateEnum.INACTIVE);
        deleteLogic(baseEntity);
        getRepository().save(baseEntity);
        return modelMapper.map(baseEntity, typeDTO());
    }

    public void deleteLogic(T baseEntity) {
    }

    @SuppressWarnings("unchecked")
    private Class<T> typeEntity() {
        ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) paramType.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    private Class<V> typeDTO() {
        ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<V>) paramType.getActualTypeArguments()[1];
    }

    public abstract BaseRepository<T, String> getRepository();
}
