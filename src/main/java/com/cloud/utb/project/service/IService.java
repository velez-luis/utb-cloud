package com.cloud.utb.project.service;

import java.util.List;

public interface IService<T, ID> {
    T create(T t) throws Exception;

    T update(T t, ID id) throws Exception;

    T logicDelete(ID id) throws Exception;

    void physicalDelete(ID id) throws Exception;

    List<T> findAll() throws Exception;

    T getById(ID id) throws Exception;

}
