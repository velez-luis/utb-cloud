package com.cloud.utb.project.service.impl;

import com.cloud.utb.project.exception.ModelNotFoundException;
import com.cloud.utb.project.repo.IGenericRepo;
import com.cloud.utb.project.service.IService;

import java.util.List;

public abstract class ServiceImpl<T, ID> implements IService<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T create(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND " + id));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T getById(ID id) {
        return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND " + id));
    }

    @Override
    public void physicalDelete(ID id) {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND " + id));
        getRepo().deleteById(id);
    }
}
