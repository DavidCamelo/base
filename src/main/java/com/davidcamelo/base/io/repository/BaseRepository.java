package com.davidcamelo.base.io.repository;

import com.davidcamelo.base.enums.StateEnum;
import com.davidcamelo.base.io.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {
    Optional<T> findByUuid(String uuid);

    Page<T> findAllByState(StateEnum state, Pageable pageable);
}
