package com.fulgay.bilenyum.service;

import java.util.List;

public interface BaseService<T> {
    public Long save(T obj);
    public List<T> findAll();
    public T findById(Long id);
    public void update(T obj);

}
