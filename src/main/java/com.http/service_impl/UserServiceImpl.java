package com.http.service_impl;

import com.http.dao.UserDao;
import com.http.dao_impl.UserDaoImpl;
import com.http.dto.UserDTO;
import com.http.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserDTO> findAll() throws SQLException {
        return null;
    }

    @Override
    public UserDTO findById(int id) throws SQLException {
        return null;
    }

    @Override
    public UserDTO insert(UserDTO userDTO) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> search(UserDTO userDTO) throws SQLException {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
