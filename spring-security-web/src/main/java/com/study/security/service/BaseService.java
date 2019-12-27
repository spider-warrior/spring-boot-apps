package com.study.security.service;

import com.study.security.dao.BaseMapper;
import com.study.security.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T extends BaseEntity, E, PK extends Serializable, M extends BaseMapper<T, E, PK>> {

    T queryById(PK id);

    List<T> queryByExample(E e);

    void removeById(PK id);

    void modify(T t);

    void save(T t);

    M getDao();

}
