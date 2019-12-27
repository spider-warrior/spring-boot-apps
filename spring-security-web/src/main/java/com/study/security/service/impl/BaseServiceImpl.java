package com.study.security.service.impl;

import com.study.security.dao.BaseMapper;
import com.study.security.entity.BaseEntity;
import com.study.security.service.BaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T extends BaseEntity, E, PK extends Serializable, M extends BaseMapper<T, E, PK>> implements BaseService<T, E, PK, M>{

    private BaseMapper<T, E, PK> baseMapper;

    @Override
    public T queryById(PK id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> queryByExample(E e) {
        return baseMapper.selectByExample(e);
    }

    @Override
    public void removeById(PK id) {
        baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void modify(T t) {
        baseMapper.updateByPrimaryKey(t);
    }

    @Override
    public void save(T t) {
        baseMapper.insert(t);
    }

    @Override
    public M getDao() {
        return (M)baseMapper;
    }

    public BaseServiceImpl(BaseMapper<T, E, PK> baseMapper) {
        this.baseMapper = baseMapper;
    }
}
