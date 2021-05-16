package com.http.service;

import java.util.List;

public interface BaseService<T> {

    List<T> findAll() throws Exception;

    T findById(Integer id) throws Exception;

    T insert(T t) throws Exception;

    List<T> search(T t) throws Exception;

    T update(T t) throws Exception;

    boolean delete(Integer id) throws Exception;

}
