package com.visitor.service;

import java.util.List;

public interface BaseService<T> {
    public List<T> getAll();
    public T add(T t);
    public T update(T t);
    public T getOneById(Integer id);
    public void delete(Integer id);
}
