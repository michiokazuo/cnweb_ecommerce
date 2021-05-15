package com.http.service_impl;

import com.http.dao.UserDao;
import com.http.dao_impl.UserDaoImpl;
import com.http.model.User;
import com.http.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

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
