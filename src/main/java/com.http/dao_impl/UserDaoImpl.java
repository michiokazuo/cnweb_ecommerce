package com.http.dao_impl;

import com.http.dao.UserDao;
import com.http.model.MyConnection;
import com.http.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public static final String NAME_USER = "user";
    private final MyConnection connection = new MyConnection();

    @Override
    public User getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<User> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public User findById(int id) throws SQLException {
        return null;
    }

    @Override
    public User insert(User user) throws SQLException {
        return null;
    }

    @Override
    public List<User> search(User user) throws SQLException {
        return null;
    }

    @Override
    public User update(User user) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
