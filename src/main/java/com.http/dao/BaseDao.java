package com.http.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface BaseDao<T> {
    T getObject(ResultSet resultSet) throws SQLException;

    List<T> getList(ResultSet resultSet) throws SQLException;

    List<T> findAll() throws SQLException;

    T findById(Integer id) throws SQLException;

    T insert(T t) throws SQLException;

    List<T> search(T t) throws SQLException;

    T update(T t) throws SQLException;

    boolean delete(Integer id, String email, Date modify) throws SQLException;

    void updateCreateAndModifyBy(String oldEmail, String newEmail) throws SQLException;
}
