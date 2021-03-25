package com.visitor.service_interfaces;

import java.util.List;

public interface BaseServiceInterface<T> {
    public List<T> getAll();
    public T add(T t);
    public T update(T t);
    public T getOneById(Integer id);
    public void delete(Integer id);
}
