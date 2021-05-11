package com.http.service;

import java.sql.SQLException;
import java.util.List;

public interface BaseService<T> {

    List<T> findAll() throws SQLException;

    T findById(int id) throws SQLException;

    T insert(T t) throws SQLException;

    List<T> search(T t) throws SQLException;

    T update(T t) throws SQLException;

    boolean delete(int id) throws SQLException;

}
